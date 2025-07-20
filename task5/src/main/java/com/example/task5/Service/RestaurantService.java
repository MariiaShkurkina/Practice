package com.example.task5.Service;
import com.example.task5.DTO.RestaurantRequestDTO;
import com.example.task5.DTO.RestaurantResponseDTO;
import com.example.task5.Entity.Restaurant;
import com.example.task5.Entity.Visitor;
import com.example.task5.Mapper.RestaurantMapper;
import com.example.task5.Repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;

    public RestaurantResponseDTO save(RestaurantRequestDTO dto) {
        Restaurant restaurant = mapper.toEntity(dto);
        repository.save(restaurant);
        return mapper.toDTO(restaurant);
    }

    public List<RestaurantResponseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public void deleteById(Long id) {

        repository.deleteById(id);
    }

    public RestaurantResponseDTO update(Long id, RestaurantRequestDTO dto) {
        Restaurant existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setCuisineType(dto.getCuisineType());
        existing.setAverageCheck(dto.getAverageCheck());

        repository.save(existing);
        return mapper.toDTO(existing);
    }
}

