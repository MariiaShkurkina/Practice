package com.example.task5.Service;

import com.example.task5.Entity.Restaurant;
import com.example.task5.Entity.Review;
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
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;



    public void save(Review review) {
        reviewRepository.save(review);
        updateRestaurantRating(review.getRestaurant());
    }

    public void remove(Review review) {
        reviewRepository.remove(review);
        updateRestaurantRating(review.getRestaurant());
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public void updateRestaurantRating(Restaurant restaurant) {
        List<Review> reviews = reviewRepository.findAll();

        // Фильтруем отзывы именно этого ресторана
        double average = reviews.stream()
                .filter(r -> r.getRestaurant().equals(restaurant))
                .mapToInt(Review::getScore)
                .average()
                .orElse(0);

        // Обновляем среднюю оценку ресторана
        restaurant.setRating(BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP));

        // Сохраняем ресторан с обновлённым рейтингом
        restaurantRepository.save(restaurant);
    }
}
