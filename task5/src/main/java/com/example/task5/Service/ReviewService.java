package com.example.task5.Service;

import com.example.task5.DTO.ReviewRequestDTO;
import com.example.task5.DTO.ReviewResponseDTO;
import com.example.task5.Entity.Restaurant;
import com.example.task5.Entity.Review;
import com.example.task5.Mapper.ReviewMapper;
import com.example.task5.Repository.RestaurantRepository;
import com.example.task5.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewMapper reviewMapper; // нужен для конвертации между DTO и сущностью

    public ReviewResponseDTO save(ReviewRequestDTO dto) {
        Review review = reviewMapper.toEntity(dto);
        Review saved = reviewRepository.save(review);
        updateRestaurantRating(saved.getRestaurant());
        return reviewMapper.toDTO(saved);
    }

    public ReviewResponseDTO update(Long id, ReviewRequestDTO dto) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        // Обновляем поля
        existing.setVisitor(dto.getVisitor());
        existing.setRestaurant(dto.getRestaurant());
        existing.setRating(dto.getRating());
        existing.setComment(dto.getComment());

        Review updated = reviewRepository.save(existing);
        updateRestaurantRating(updated.getRestaurant());
        return reviewMapper.toDTO(updated);
    }

    public void remove(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        reviewRepository.delete(review);
        updateRestaurantRating(review.getRestaurant());
    }

    public List<ReviewResponseDTO> findAll() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    private void updateRestaurantRating(Restaurant restaurant) {
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurant.getId());

        double average = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);

        restaurant.setRating(BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP));
        restaurantRepository.save(restaurant);
    }

    public ReviewResponseDTO findById(Long id) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));


        return reviewMapper.toDTO(existing);
    }

}
