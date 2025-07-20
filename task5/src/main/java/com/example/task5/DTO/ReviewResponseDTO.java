package com.example.task5.DTO;

import com.example.task5.Entity.Restaurant;
import com.example.task5.Entity.Visitor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Ответ с данными отзыва")
public class ReviewResponseDTO {

    @Schema(description = "ID отзыва", example = "1")
    Long id;

    @Schema(description = "Посетитель, оставивший отзыв")
    Visitor visitor;

    @Schema(description = "Ресторан, к которому относится отзыв")
    Restaurant restaurant;

    @Schema(description = "Оценка ресторана", example = "4")
    int rating;

    @Schema(description = "Комментарий к отзыву")
    String comment;
}