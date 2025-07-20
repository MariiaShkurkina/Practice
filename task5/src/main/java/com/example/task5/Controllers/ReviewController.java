package com.example.task5.Controllers;

import com.example.task5.DTO.ReviewRequestDTO;
import com.example.task5.DTO.ReviewResponseDTO;
import com.example.task5.Entity.Review;
import com.example.task5.Service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Review", description = "API для управления отзывами")
public class ReviewController {

    private final ReviewService service;

    @Operation(summary = "Получить все отзывы")
    @ApiResponse(responseCode = "200", description = "Список отзывов")
    @GetMapping
    public List<ReviewResponseDTO> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Создать новый отзыв")
    @ApiResponse(responseCode = "201", description = "Отзыв создан")
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> create(
            @RequestBody
            @Parameter(description = "Данные нового отзыва", required = true)
            ReviewRequestDTO dto) {
        ReviewResponseDTO response = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Обновить отзыв по ID")
    @ApiResponse(responseCode = "200", description = "Отзыв обновлён")
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> update(
            @Parameter(description = "ID отзыва", required = true)
            @PathVariable Long id,
            @RequestBody
            @Parameter(description = "Обновлённые данные отзыва", required = true)
            ReviewRequestDTO dto) {
        ReviewResponseDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Удалить отзыв по ID")
    @ApiResponse(responseCode = "204", description = "Отзыв удалён")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID отзыва", required = true)
            @PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
