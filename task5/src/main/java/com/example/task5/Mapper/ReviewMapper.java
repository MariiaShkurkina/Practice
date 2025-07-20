package com.example.task5.Mapper;

import com.example.task5.DTO.ReviewRequestDTO;
import com.example.task5.DTO.ReviewResponseDTO;
import com.example.task5.Entity.Review;
import com.example.task5.Repository.RestaurantRepository;
import com.example.task5.Repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// ReviewMapper.java
@Component
@RequiredArgsConstructor
public class ReviewMapper {

    public Review toEntity(ReviewRequestDTO dto) {
        return new Review(
                null,
                dto.getVisitor(),
                dto.getRestaurant(),
                dto.getRating(),
                dto.getComment()
        );
    }

    public ReviewResponseDTO toDTO(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getVisitor(),
                review.getRestaurant(),
                review.getScore(),
                review.getComment()
        );
    }
}
