package com.example.task5.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Ответ с данными посетителя")
public class VisitorResponseDTO {

    @Schema(description = "ID посетителя", example = "1")
    Long id;

    @Schema(description = "Имя посетителя")
    String name;

    @Schema(description = "Возраст посетителя")
    int age;

    @Schema(description = "Пол посетителя")
    String gender;
}