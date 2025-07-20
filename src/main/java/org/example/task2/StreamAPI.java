package org.example.task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String[] args) {
        List<Car> carList = Arrays.asList(
                new Car("1HGCM82633A123456", "Accord", "Honda", 2020, 15000, 22000,CarType.ELECTRIC),
                new Car("1HGCM82633A123457", "Civic", "Honda", 2019, 30000, 18000, CarType.SUV),
                new Car("2HGFG12658H123456", "Civic", "Honda", 2018, 40000, 20000, CarType.SEDAN),
                new Car("3FA6P0H7XJR123456", "Fusion", "Ford", 2019, 25000, 21000, CarType.COUPE),
                new Car("3FA6P0H7XJR123457", "Mustang", "Ford", 2021, 60000, 30000, CarType.CONVERTIBLE),
                new Car("4T1BF1FK6HU123456", "Camry", "Toyota", 2020, 45000, 24000, CarType.HATCHBACK)
        );
        // Отфильтруйте только машины с пробегом меньше 50_000 км
        List<Car> filteredCars = carList.stream()
                .filter(car -> car.getMileage() < 50000)
                .toList();

        // Отсортируйте по цене (по убыванию)
        List<Car> sortedCars = filteredCars.stream()
                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                .toList();

        // Выведите топ-3 самые дорогие машины
        System.out.println("Top 3 самые дорогие машины:");
        sortedCars.stream()
                .limit(3)
                .forEach(System.out::println);

        // Посчитайте средний пробег всех машин
        double averageMileage = carList.stream()
                .mapToDouble(Car::getMileage)
                .average()
                .orElse(0.0);
        System.out.println("Средний пробег всех машин: " + averageMileage);

        // Сгруппируйте машины по производителю в Map<String, List<Car>>
        Map<String, List<Car>> carsByManufacturer = carList.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));

        System.out.println("Сгруппированные машины по производителю:");
        carsByManufacturer.forEach((manufacturer, cars) -> {
            System.out.println(manufacturer + ": " + cars);
        });
    }
}
