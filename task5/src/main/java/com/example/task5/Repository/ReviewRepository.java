package com.example.task5.Repository;

import com.example.task5.Entity.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();

    public void save(Review review) {
        reviews.add(review);
    }

    public void remove(Review review) {
        reviews.remove(review);
    }

    public List<Review> findAll() {
        return Collections.unmodifiableList(reviews);
    }

    public Optional<Review> findById(Long id) {
        return reviews.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }
}
