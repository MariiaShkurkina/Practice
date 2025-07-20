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
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewMapper mapper;

    public ReviewResponseDTO save(ReviewRequestDTO dto) {
        Review review = mapper.toEntity(dto);

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurant().getId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + dto.getRestaurant().getId()));
        review.setRestaurant(restaurant);

        repository.save(review);
        updateRestaurantRating(restaurant);

        return mapper.toDTO(review);
    }

    public List<ReviewResponseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public void deleteById(Long id) {
        Review review = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        repository.remove(review);
        updateRestaurantRating(review.getRestaurant());
    }

    public ReviewResponseDTO update(Long id, ReviewRequestDTO dto) {
        Review existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        existing.setComment(dto.getComment());
        existing.setScore(dto.getRating());

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurant().getId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + dto.getRestaurant().getId()));
        existing.setRestaurant(restaurant);

        repository.save(existing);
        updateRestaurantRating(restaurant);

        return mapper.toDTO(existing);
    }

    private void updateRestaurantRating(Restaurant restaurant) {
        List<Review> reviews = repository.findAll().stream()
                .filter(r -> r.getRestaurant().equals(restaurant))
                .toList();

        double avg = reviews.stream()
                .mapToInt(Review::getScore)
                .average()
                .orElse(0);

        restaurant.setRating(BigDecimal.valueOf(avg).setScale(2, RoundingMode.HALF_UP));
        restaurantRepository.save(restaurant);
    }
}
