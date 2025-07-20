package com.example.task5.Repository;

import com.example.task5.Entity.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();

    public void save(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void remove(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }

    public List<Restaurant> findAll() {
        return Collections.unmodifiableList(restaurants);
    }
    public Optional<Restaurant> findById(Long id) {
        return restaurants.stream()
                .filter(r -> Objects.equals(r.getId(), id))
                .findFirst();
    }
}
