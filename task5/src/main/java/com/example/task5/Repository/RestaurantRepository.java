package com.example.task5.Repository;

import com.example.task5.Entity.Restaurant;
import com.example.task5.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

