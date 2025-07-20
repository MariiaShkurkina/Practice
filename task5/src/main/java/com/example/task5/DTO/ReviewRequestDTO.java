package com.example.task5.DTO;

import com.example.task5.Entity.Restaurant;
import com.example.task5.Entity.Visitor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Запрос на создание/обновление отзыва")
public class ReviewRequestDTO {

    @Schema(description = "Посетитель, оставивший отзыв", required = true)
    Visitor visitor;

    @Schema(description = "Ресторан, к которому относится отзыв", required = true)
    Restaurant restaurant;

    @Schema(description = "Оценка ресторана (например, от 1 до 5)", example = "4", required = true)
    int rating;

    @Schema(description = "Комментарий к отзыву", example = "Очень понравилось!")
    String comment;
}