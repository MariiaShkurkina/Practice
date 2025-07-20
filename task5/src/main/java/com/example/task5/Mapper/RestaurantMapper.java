package com.example.task5.Mapper;

import com.example.task5.DTO.RestaurantRequestDTO;
import com.example.task5.DTO.RestaurantResponseDTO;
import com.example.task5.Entity.CuisineType;
import com.example.task5.Entity.Restaurant;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

// RestaurantMapper.java
@Component
public class RestaurantMapper {
    public Restaurant toEntity(RestaurantRequestDTO dto) {
        return new Restaurant(null, dto.getName(), dto.getDescription(),  dto.getCuisineType(), dto.getAverageCheck(),BigDecimal.ZERO);
    }

    public RestaurantResponseDTO toDTO(Restaurant entity) {
        return new RestaurantResponseDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getCuisineType(), entity.getAverageCheck(),entity.getRating());
    }
}
