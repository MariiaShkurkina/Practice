package org.example.task2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Car implements Comparable<Car>{
    private String vin;
    private String model;
    private String manufacturer;
    private int year;
    private double mileage;
    private double price;
    private CarType type;

    public Car(String vin, String model, String manufacturer, int year, double mileage, double price, CarType type) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getYear() {
        return year;
    }

    public double getMileage() {
        return mileage;
    }

    public double getPrice() {
        return price;
    }
    public CarType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car other_car = (Car) o;
        return Objects.equals(this.vin, other_car.vin);
    }
    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }
    @Override
    public String toString() {
        return "Car{" +
                "vin=" + vin +
        ", model=" + model +
        ", manufacturer='" + manufacturer +
        ", year=" + year +
                ", mileage"+mileage+
                ", price=" + price +
                '}';
    }
    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.year, this.year); // Сортировка по году выпуска (от новых к старым)
    }
    public static void main(String[] args) {
        Set<Car> carSet = new HashSet<>();
        carSet.add(new Car("1HGCM82633A123456", "CRX", "Honda", 2020, 15000, 22000, CarType.SUV));
        carSet.add(new Car("1HGCM82633A123456", "Accord", "Honda", 2020, 15000, 22000,CarType.SEDAN)); // Дубликат по VIN
        carSet.add(new Car("2HGFG12658H123456", "Civic", "Honda", 2018, 30000, 18000,CarType.ELECTRIC));
        carSet.add(new Car("3FA6P0H7XJR123456", "Fusion", "Ford", 2019, 25000, 21000,CarType.SUV));

        System.out.println("Автомобили в HashSet:");
        for (Car car : carSet) {
            System.out.println(car);
        }

        // Сортировка с использованием TreeSet
        Set<Car> sortedCarSet = new TreeSet<>(carSet);

        System.out.println("Автомобили, отсортированные по году выпсука:");
        for (Car car : sortedCarSet) {
            System.out.println(car);
        }
    }


}

