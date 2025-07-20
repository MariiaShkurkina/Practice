package com.example.task5;

import com.example.task5.Controllers.RestaurantController;
import com.example.task5.DTO.RestaurantRequestDTO;
import com.example.task5.DTO.RestaurantResponseDTO;
import com.example.task5.Entity.CuisineType;
import com.example.task5.Service.RestaurantService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRestaurants() throws Exception {
        RestaurantResponseDTO restaurant = new RestaurantResponseDTO(
                1L, "La Piazza", "Italian fine dining",
                CuisineType.ITALIAN, new BigDecimal("45.00"), new BigDecimal("4.8")
        );

        Mockito.when(restaurantService.findAll()).thenReturn(List.of(restaurant));

        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("La Piazza")))
                .andExpect(jsonPath("$[0].userRating", is(4.8)));
    }

    @Test
    void testCreateRestaurant() throws Exception {
        RestaurantRequestDTO request = new RestaurantRequestDTO(
                "La Piazza", "Italian fine dining",
                CuisineType.ITALIAN, new BigDecimal("45.00")
        );

        RestaurantResponseDTO response = new RestaurantResponseDTO(
                1L, "La Piazza", "Italian fine dining",
                CuisineType.ITALIAN, new BigDecimal("45.00"), new BigDecimal("0.0")
        );

        Mockito.when(restaurantService.save(any())).thenReturn(response);

        mockMvc.perform(post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("La Piazza"));
    }

    @Test
    void testUpdateRestaurant() throws Exception {
        RestaurantRequestDTO request = new RestaurantRequestDTO(
                "La Piazza Updated", "Updated description",
                CuisineType.ITALIAN, new BigDecimal("50.00")
        );

        RestaurantResponseDTO response = new RestaurantResponseDTO(
                1L, "La Piazza Updated", "Updated description",
                CuisineType.ITALIAN, new BigDecimal("50.00"), new BigDecimal("4.5")
        );

        Mockito.when(restaurantService.update(Mockito.eq(1L), any())).thenReturn(response);

        mockMvc.perform(put("/api/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("La Piazza Updated"))
                .andExpect(jsonPath("$.averageCheck").value(50.00));
    }

    @Test
    void testDeleteRestaurant() throws Exception {
        Mockito.doNothing().when(restaurantService).deleteById(1L);

        mockMvc.perform(delete("/api/restaurants/1"))
                .andExpect(status().isNoContent());
    }
}
