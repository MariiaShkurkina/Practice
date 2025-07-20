package com.example.task5;

import com.example.task5.Controllers.ReviewController;
import com.example.task5.DTO.ReviewRequestDTO;
import com.example.task5.DTO.ReviewResponseDTO;
import com.example.task5.Entity.CuisineType;
import com.example.task5.Entity.Restaurant;
import com.example.task5.Entity.Visitor;
import com.example.task5.Service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Visitor visitor = new Visitor(1L, "Иван", 30, "Мужской");
    private final Restaurant restaurant = new Restaurant(1L, "La Piazza", "Fine Italian",CuisineType.ITALIAN, new BigDecimal("45.00"), new BigDecimal("4.8"));

    @Test
    void testGetAllReviews() throws Exception {
        ReviewResponseDTO review = new ReviewResponseDTO(1L, visitor, restaurant, 5, "Отлично");

        Mockito.when(reviewService.findAll()).thenReturn(List.of(review));

        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rating", is(5)))
                .andExpect(jsonPath("$[0].comment", is("Отлично")));
    }

    @Test
    void testCreateReview() throws Exception {
        ReviewRequestDTO request = new ReviewRequestDTO(visitor, restaurant, 4, "Очень понравилось");
        ReviewResponseDTO response = new ReviewResponseDTO(1L, visitor, restaurant, 4, "Очень понравилось");

        Mockito.when(reviewService.save(any())).thenReturn(response);

        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rating").value(4))
                .andExpect(jsonPath("$.comment").value("Очень понравилось"));
    }

    @Test
    void testUpdateReview() throws Exception {
        ReviewRequestDTO request = new ReviewRequestDTO(visitor, restaurant, 3, "Хорошо, но не идеально");
        ReviewResponseDTO response = new ReviewResponseDTO(1L, visitor, restaurant, 3, "Хорошо, но не идеально");

        Mockito.when(reviewService.update(Mockito.eq(1L), any())).thenReturn(response);

        mockMvc.perform(put("/api/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(3))
                .andExpect(jsonPath("$.comment").value("Хорошо, но не идеально"));
    }

    @Test
    void testDeleteReview() throws Exception {
        doNothing().when(reviewService).remove(1L);

        mockMvc.perform(delete("/api/reviews/1"))
                .andExpect(status().isNoContent());
    }
}
