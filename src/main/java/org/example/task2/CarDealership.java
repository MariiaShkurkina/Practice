package org.example.task2;

import java.util.*;
import java.util.stream.Collectors;

public class CarDealership {
    private Set<Car> cars;

    public CarDealership() {
        this.cars = new HashSet<>();
    }

    // Метод для добавления автомобиля
    public boolean addCar(Car car) {
        return cars.add(car);
    }
    //Метод для поиска авто по производителю
    public List<Car> findCarsByManufacturer(String manufacturer){
        return cars.stream()
                .filter(car->car.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }
    //Метод для вывода средней цены автомобилей определенного типа
    public double averagePriceByType(CarType type) {
        return cars.stream()
                .filter(car -> car.getType() == type)
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0); // Возвращаем 0, если нет автомобилей данного типа
    }
    //Метод для полученя списка машин, отсортированных по году выпуска(от новых к  старым)
    public List<Car> getCarsSortedByYear()
    {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getYear).reversed())
                .collect(Collectors.toList());
    }
    //Метод полученя ключ-знаечние тип машины - кол-во
    public Map<CarType, Long> getCountCarsByType(){
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getType,Collectors.counting()));

    }
    // Метод для нахождения самой старой и самой новой машины в наличии
    public Map<String, Car> findOldestAndNewestCar() {

        Car oldest = cars.stream()
                .min(Comparator.comparingInt(Car::getYear))
                .orElse(null);

        Car newest = cars.stream()
                .max(Comparator.comparingInt(Car::getYear))
                .orElse(null);

        Map<String, Car> result = new HashMap<>();
        result.put("самая старая", oldest);
        result.put("самая новая", newest);

        return result;
    }


}
