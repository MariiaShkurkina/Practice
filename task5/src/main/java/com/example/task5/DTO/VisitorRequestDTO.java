package com.example.task5.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Запрос на создание/обновление посетителя")
public class VisitorRequestDTO {

    @Schema(description = "Имя посетителя", example = "Иван Иванов", required = true)
    String name;

    @Schema(description = "Возраст посетителя", example = "30", required = true)
    int age;

    @Schema(description = "Пол посетителя", example = "Мужской", required = true)
    String gender;
}
