package com.example.task5.Service;

import com.example.task5.DTO.VisitorRequestDTO;
import com.example.task5.DTO.VisitorResponseDTO;
import com.example.task5.Entity.Visitor;
import com.example.task5.Mapper.VisitorMapper;
import com.example.task5.Repository.VisitorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitorService {
    private final VisitorRepository repository;
    private final VisitorMapper mapper;

    public VisitorResponseDTO save(VisitorRequestDTO dto) {
        Visitor visitor = mapper.toEntity(dto);
        repository.save(visitor);
        return mapper.toDTO(visitor);
    }

    public List<VisitorResponseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }


    public void deleteById(Long id) {
        Visitor visitor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found with id: " + id));
        repository.remove(visitor);
    }

    public VisitorResponseDTO update(Long id, VisitorRequestDTO dto) {
        Visitor existingVisitor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found with id: " + id));

        existingVisitor.setName(dto.getName());
        existingVisitor.setAge(dto.getAge());
        existingVisitor.setGender(dto.getGender());

        repository.save(existingVisitor);

        return mapper.toDTO(existingVisitor);
    }
}
