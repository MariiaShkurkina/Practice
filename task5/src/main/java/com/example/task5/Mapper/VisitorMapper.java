package com.example.task5.Mapper;

import com.example.task5.DTO.VisitorRequestDTO;
import com.example.task5.DTO.VisitorResponseDTO;
import com.example.task5.Entity.Visitor;
import org.springframework.stereotype.Component;

// VisitorMapper.java
@Component
public class VisitorMapper {
    public Visitor toEntity(VisitorRequestDTO dto) {
        return new Visitor(null, dto.getName(), dto.getAge(), dto.getGender());
    }

    public VisitorResponseDTO toDTO(Visitor entity) {
        return new VisitorResponseDTO(entity.getId(), entity.getName(), entity.getAge(), entity.getGender());
    }
}
