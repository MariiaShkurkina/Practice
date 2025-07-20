package com.example.task5.DTO;

import com.example.task5.Entity.CuisineType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.math.BigDecimal;


@Value
public class RestaurantRequestDTO {

    @Schema(description = "Название ресторана", example = "La Piazza")
    String name;

    String description;

    @Schema(description = "Тип кухни", example = "Итальянская")
    CuisineType cuisineType;

    BigDecimal averageCheck;

}
