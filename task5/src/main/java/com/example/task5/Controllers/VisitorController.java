package com.example.task5.Controllers;

import com.example.task5.DTO.VisitorRequestDTO;
import com.example.task5.DTO.VisitorResponseDTO;
import com.example.task5.Entity.Visitor;
import com.example.task5.Service.VisitorService;
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
@RequestMapping("/api/visitors")
@RequiredArgsConstructor
@Tag(name = "Visitor", description = "API для управления посетителями")
public class VisitorController {

    private final VisitorService service;

    @Operation(summary = "Получить всех посетителей")
    @ApiResponse(responseCode = "200", description = "Список посетителей")
    @GetMapping
    public List<VisitorResponseDTO> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Создать нового посетителя")
    @ApiResponse(responseCode = "201", description = "Посетитель создан")
    @PostMapping
    public ResponseEntity<VisitorResponseDTO> create(
            @RequestBody
            @Parameter(description = "Данные нового посетителя", required = true)
            VisitorRequestDTO dto) {
        VisitorResponseDTO response = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Обновить данные посетителя по ID")
    @ApiResponse(responseCode = "200", description = "Посетитель обновлён")
    @PutMapping("/{id}")
    public ResponseEntity<VisitorResponseDTO> update(
            @Parameter(description = "ID посетителя", required = true)
            @PathVariable Long id,
            @RequestBody
            @Parameter(description = "Обновлённые данные посетителя", required = true)
            VisitorRequestDTO dto) {
        VisitorResponseDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Удалить посетителя по ID")
    @ApiResponse(responseCode = "204", description = "Посетитель удалён")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID посетителя", required = true)
            @PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
