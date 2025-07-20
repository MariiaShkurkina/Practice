package com.example.task5;

import com.example.task5.DTO.RestaurantRequestDTO;
import com.example.task5.DTO.RestaurantResponseDTO;
import com.example.task5.Entity.CuisineType;
import com.example.task5.Entity.Restaurant;
import com.example.task5.Mapper.RestaurantMapper;
import com.example.task5.Repository.RestaurantRepository;
import com.example.task5.Service.RestaurantService;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository repository;

    @Mock
    private RestaurantMapper mapper;

    @InjectMocks
    private RestaurantService service;

    public RestaurantServiceTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateRestaurant() {
        // Данные запроса
        RestaurantRequestDTO request = new RestaurantRequestDTO(
                "La Piazza",
                "Nice Italian",
                CuisineType.ITALIAN,
                new BigDecimal("150.00")
        );

        // Сущность, которую ожидаем сохранить
        Restaurant restaurantEntity = new Restaurant(null, "La Piazza", "Nice Italian", CuisineType.ITALIAN, new BigDecimal("150.00"), BigDecimal.ZERO);

        // Сущность после сохранения (с id)
        Restaurant savedRestaurant = new Restaurant(1L, "La Piazza", "Nice Italian", CuisineType.ITALIAN, new BigDecimal("150.00"), BigDecimal.ZERO);

        // DTO для ответа
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO(1L, "La Piazza", "Nice Italian", CuisineType.ITALIAN, new BigDecimal("150.00"), BigDecimal.ZERO);

        // Мокируем маппинг запроса в сущность
        when(mapper.toEntity(request)).thenReturn(restaurantEntity);

        // Мокируем сохранение
        when(repository.save(restaurantEntity)).thenReturn(savedRestaurant);

        // Мокируем маппинг сущности в DTO
        when(mapper.toDTO(savedRestaurant)).thenReturn(responseDTO);

        // Вызов метода create
        RestaurantResponseDTO result = service.save(request);

        // Проверки
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("La Piazza", result.getName());
        assertEquals(new BigDecimal("150.00"), result.getAverageCheck());

        verify(mapper).toEntity(request);
        verify(repository).save(restaurantEntity);
        verify(mapper).toDTO(savedRestaurant);
    }


    @Test
    void testFindById() {
        Long id = 1L;
        BigDecimal price1=new BigDecimal("150.00");
        BigDecimal rating1=new BigDecimal("5.00");
        Restaurant entity = new Restaurant(id, "La Piazza","Nice Italian",CuisineType.ITALIAN,price1,rating1);
        RestaurantResponseDTO dto = new RestaurantResponseDTO(id, "La Piazza", "Nice Italian", CuisineType.ITALIAN, new BigDecimal("150.00"), new BigDecimal("5.00"));

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        RestaurantResponseDTO result = service.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("La Piazza", result.getName());
        assertEquals(new BigDecimal("150.00"), result.getAverageCheck());
        assertEquals(new BigDecimal("5.00"), result.getUserRating());
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        BigDecimal rating1 = new BigDecimal("4.50");
        BigDecimal rating2 = new BigDecimal("4.00");
        RestaurantRequestDTO updateRequest = new RestaurantRequestDTO("La Piazza Updated", "Updated desc", CuisineType.ITALIAN, new BigDecimal("170.00"));
        Restaurant existing = new Restaurant(id, "La Piazza", "Nice Italian", CuisineType.ITALIAN, new BigDecimal("150.00"), rating1);
        Restaurant updated = new Restaurant(id, "La Piazza Updated", "Updated desc", CuisineType.ITALIAN, new BigDecimal("170.00"), rating2);
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO(id, "La Piazza Updated", "Updated desc", CuisineType.ITALIAN, new BigDecimal("170.00"), new BigDecimal("4.75"));

        when(repository.findById(id)).thenReturn(Optional.of(existing));
        when(repository.save(any(Restaurant.class))).thenReturn(updated);
        when(mapper.toDTO(updated)).thenReturn(responseDTO);

        RestaurantResponseDTO result = service.update(id, updateRequest);

        assertNotNull(result);
        assertEquals("La Piazza Updated", result.getName());
        assertEquals(new BigDecimal("170.00"), result.getAverageCheck());
    }


    @Test
    void testDeleteById() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);
        service.deleteById(id);
        verify(repository, times(1)).deleteById(id);
    }
}
