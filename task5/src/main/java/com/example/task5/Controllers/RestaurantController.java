package com.example.task5.Controllers;

import com.example.task5.DTO.RestaurantRequestDTO;
import com.example.task5.DTO.RestaurantResponseDTO;
import com.example.task5.Entity.Restaurant;
import com.example.task5.Service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurants", description = "API для управления ресторанами")
public class RestaurantController {

    private final RestaurantService service;

    @Operation(summary = "Получить список всех ресторанов")
    @GetMapping
    public List<RestaurantResponseDTO> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Создать новый ресторан")
    @ApiResponse(responseCode = "201", description = "Ресторан создан")
    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> create(@RequestBody RestaurantRequestDTO dto) {
        RestaurantResponseDTO response = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Обновить ресторан по ID")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> update(@PathVariable Long id, @RequestBody RestaurantRequestDTO dto) {
        RestaurantResponseDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Удалить ресторан по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
