package com.example.task5;

import com.example.task5.DTO.VisitorRequestDTO;
import com.example.task5.DTO.VisitorResponseDTO;
import com.example.task5.Entity.Visitor;
import com.example.task5.Mapper.VisitorMapper;
import com.example.task5.Repository.VisitorRepository;
import com.example.task5.Service.VisitorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VisitorServiceTest {

    @Mock
    private VisitorRepository repository;

    @Mock
    private VisitorMapper mapper;

    @InjectMocks
    private VisitorService service;

    @Test
    void testSaveUser() {
        VisitorRequestDTO request = new VisitorRequestDTO("Alice", 22, "Ж");
        Visitor entity = new Visitor(null, "Alice", 22, "Ж");
        Visitor saved = new Visitor(1L, "Alice", 22, "Ж");
        VisitorResponseDTO response = new VisitorResponseDTO(1L, "Alice", 22, "Ж");

        Mockito.when(mapper.toEntity(request)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(saved);
        Mockito.when(mapper.toDTO(saved)).thenReturn(response);

        VisitorResponseDTO result = service.save(request);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Visitor entity = new Visitor(id, "Bob", 30, "М");
        VisitorResponseDTO response = new VisitorResponseDTO(id, "Bob", 30, "М");

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toDTO(entity)).thenReturn(response);

        VisitorResponseDTO result = service.findById(id);

        assertNotNull(result);
        assertEquals("Bob", result.getName());
        assertEquals(30, result.getAge());
    }

    @Test
    void testFindById_NotFound() {
        Long id = 100L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.findById(id);
        });

        assertEquals("Visitor not found with id: 100", exception.getMessage());
    }

    @Test
    void testUpdateVisitor() {
        Long id = 1L;
        VisitorRequestDTO request = new VisitorRequestDTO("Updated", 40, "Ж");
        Visitor existing = new Visitor(id, "Old", 30, "М");
        Visitor updated = new Visitor(id, "Updated", 40, "Ж");
        VisitorResponseDTO response = new VisitorResponseDTO(id, "Updated", 40, "Ж");

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(existing));
        Mockito.when(repository.save(existing)).thenReturn(updated);
        Mockito.when(mapper.toDTO(updated)).thenReturn(response);

        VisitorResponseDTO result = service.update(id, request);

        assertNotNull(result);
        assertEquals("Updated", result.getName());
        assertEquals(40, result.getAge());
    }

    @Test
    void testDeleteVisitor() {
        Long id = 1L;

        // Тут можно просто проверить, что deleteById был вызван
        service.deleteById(id);

        Mockito.verify(repository).deleteById(id);
    }
}
