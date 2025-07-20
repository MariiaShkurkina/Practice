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

    @GetMapping
    @Operation(summary = "Получить список всех отзывов")
    public List<ReviewResponseDTO> getAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Создать новый отзыв")
    public ResponseEntity<ReviewResponseDTO> create(@RequestBody ReviewRequestDTO dto) {
        ReviewResponseDTO response = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить отзыв по ID")
    public ResponseEntity<ReviewResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ReviewRequestDTO dto) {
        ReviewResponseDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить отзыв по ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}
