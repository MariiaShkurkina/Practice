package com.example.task5;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.task5.DTO.ReviewRequestDTO;
import com.example.task5.DTO.ReviewResponseDTO;
import com.example.task5.Entity.CuisineType;
import com.example.task5.Entity.Restaurant;
import com.example.task5.Entity.Review;
import com.example.task5.Entity.Visitor;
import com.example.task5.Mapper.ReviewMapper;
import com.example.task5.Repository.RestaurantRepository;
import com.example.task5.Repository.ReviewRepository;
import com.example.task5.Repository.VisitorRepository;
import com.example.task5.Service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private RestaurantRepository restaurantRepository;  // Мок для ресторана

    @Mock
    private VisitorRepository visitorRepository;

    @Mock
    private ReviewMapper mapper;

    @InjectMocks
    private ReviewService reviewService;

    public ReviewServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSaveReview() {
        Visitor visitor = new Visitor(2L, "John", 30, "M");
        Restaurant restaurant = new Restaurant(3L, "La Piazza", "Nice Italian", CuisineType.ITALIAN, new BigDecimal("150.00"), new BigDecimal("4.5"));

        ReviewRequestDTO requestDTO = new ReviewRequestDTO(
                visitor,
                restaurant,
                5,
                "Excellent!"
        );

        Review reviewEntity = new Review(
                null,
                visitor,
                restaurant,
                5,
                "Excellent!"
        );

        Review savedReview = new Review(
                1L,
                visitor,
                restaurant,
                5,
                "Excellent!"
        );

        ReviewResponseDTO responseDTO = new ReviewResponseDTO(
                1L,
                visitor,
                restaurant,
                5,
                "Excellent!"
        );

        when(mapper.toEntity(requestDTO)).thenReturn(reviewEntity);
        when(reviewRepository.save(reviewEntity)).thenReturn(savedReview);
        when(mapper.toDTO(savedReview)).thenReturn(responseDTO);

        ReviewResponseDTO result = reviewService.save(requestDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(5, result.getRating());
        assertEquals("Excellent!", result.getComment());

        verify(mapper).toEntity(requestDTO);
        verify(reviewRepository).save(reviewEntity);
        verify(mapper).toDTO(savedReview);
    }


    @Test
    void testFindById() {
        Visitor visitorEntity = new Visitor(2L, "Alice", 22, "Ж");
        Restaurant restaurantEntity = new Restaurant(3L, "La Piazza", "Nice Italian", CuisineType.ITALIAN, new BigDecimal("150.00"),new BigDecimal("5.00"));
        Long id = 1L;
        Review entity = new Review(id, visitorEntity, restaurantEntity, 4, "Nice!");
        ReviewResponseDTO dto = new ReviewResponseDTO(id, visitorEntity, restaurantEntity, 4, "Nice!");

        when(reviewRepository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        ReviewResponseDTO result = reviewService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(4, result.getRating());
        assertEquals("Nice!", result.getComment());
    }


    @Test
    void testUpdate() {
        Long reviewId = 1L;
        Long visitorId = 2L;
        Long restaurantId = 3L;

        // Исходные сущности
        Visitor visitor = new Visitor(visitorId, "John", 30, "M");
        Restaurant restaurant = new Restaurant(restaurantId, "La Piazza", "Nice Italian", CuisineType.ITALIAN, new BigDecimal("150.00"), new BigDecimal("4.5"));
        Review existingReview = new Review(reviewId, visitor, restaurant, 4, "Good food");

        // DTO для обновления
        Visitor visitorDTO = new Visitor(visitorId, "John", 30, "M");
        Restaurant restaurantDTO = new Restaurant(restaurantId, "La Piazza", "Nice Italian", CuisineType.ITALIAN, new BigDecimal("150.00"), new BigDecimal("4.5"));
        ReviewRequestDTO updateDTO = new ReviewRequestDTO(visitorDTO, restaurantDTO, 5, "Excellent!");

        // Обновленные сущности
        Review updatedReview = new Review(reviewId, visitor, restaurant, 5, "Excellent!");

        // Ответный DTO
        ReviewResponseDTO responseDTO = new ReviewResponseDTO(reviewId, visitorDTO, restaurantDTO, 5, "Excellent!");

        // Мокируем вызовы репозиториев и маппера
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(existingReview)).thenReturn(updatedReview);
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);  // если updateRestaurantRating вызывает save
        when(mapper.toDTO(updatedReview)).thenReturn(responseDTO);

        // Запускаем тестируемый метод
        ReviewResponseDTO result = reviewService.update(reviewId, updateDTO);

        // Проверки
        assertNotNull(result);
        assertEquals(reviewId, result.getId());
        assertEquals(5, result.getRating());
        assertEquals("Excellent!", result.getComment());

        // Проверяем, что методы репозиториев вызвались как ожидалось
        verify(reviewRepository).findById(reviewId);
        verify(reviewRepository).save(existingReview);
        verify(restaurantRepository).save(restaurant);
        verify(mapper).toDTO(updatedReview);
    }


}
