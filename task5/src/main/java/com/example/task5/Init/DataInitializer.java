package com.example.task5.Init;

import com.example.task5.Entity.CuisineType;
import com.example.task5.Entity.Restaurant;
import com.example.task5.Entity.Review;
import com.example.task5.Entity.Visitor;
import com.example.task5.Service.RestaurantService;
import com.example.task5.Service.ReviewService;
import com.example.task5.Service.VisitorService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
//
//@Component
//public class DataInitializer {
//
//    private final VisitorService visitorService;
//    private final RestaurantService restaurantService;
//    private final ReviewService reviewService;
//
//    public DataInitializer(VisitorService visitorService,
//                           RestaurantService restaurantService,
//                           ReviewService reviewService) {
//        this.visitorService = visitorService;
//        this.restaurantService = restaurantService;
//        this.reviewService = reviewService;
//    }
//
//    @PostConstruct
//    public void init() {
//        // Создаём посетителей
//        Visitor v1 = new Visitor(null, "Анна", 30, "Ж");
//        Visitor v2 = new Visitor(null, "Иван", 25, "М");
//        visitorService.save(v1);
//        visitorService.save(v2);
//
//        // Создаём рестораны
//        Restaurant r1 = new Restaurant(null, "Bella Italia", "Итальянская кухня", CuisineType.ITALIAN, new BigDecimal("700"), BigDecimal.ZERO);
//        Restaurant r2 = new Restaurant(null, "Golden Dragon", "Азиатская кухня", CuisineType.ASIAN, new BigDecimal("500"), BigDecimal.ZERO);
//        restaurantService.save(r1);
//        restaurantService.save(r2);
//
//        // Добавляем оценки
//        Review review1 = new Review(null, v1, r1, 5, "Отлично!");
//        Review review2 = new Review(null, v2, r1, 4, "Очень вкусно");
//        Review review3 = new Review(null, v2, r2, 3, "Нормально");
//
//        reviewService.save(review1);
//        reviewService.save(review2);
//        reviewService.save(review3);
//
//        // Проверяем рейтинг после добавления
//        System.out.println("Средний рейтинг " + r1.getName() + ": " + r1.getRating());
//        System.out.println("Средний рейтинг " + r2.getName() + ": " + r2.getRating());
//    }
//}
