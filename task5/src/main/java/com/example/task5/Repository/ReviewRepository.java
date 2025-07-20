package com.example.task5.Repository;

import com.example.task5.DTO.ReviewResponseDTO;
import com.example.task5.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurantId(Long restaurantId);
}

