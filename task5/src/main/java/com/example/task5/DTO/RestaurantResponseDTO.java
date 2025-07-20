package com.example.task5.DTO;

import com.example.task5.Entity.CuisineType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class RestaurantResponseDTO {
    @Schema(description = "ID ресторана", example = "1")
    Long id;
    @Schema(description = "Название ресторана")
    String name;

    String description;

    CuisineType cuisineType;

    @Schema(description = "Тип кухни")
    BigDecimal averageCheck;

    @Schema(description = "Рейтинг", example = "4.75")
    BigDecimal userRating;

}
