package org.example.task2;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        CarDealership dealership = new CarDealership();

// Добавление автомобилей в автоцентр
        dealership.addCar(new Car("1HGCM82633A123456", "Accord", "Honda", 2020, 15000, 22000, CarType.SEDAN));
        dealership.addCar(new Car("1HGCM82633A123457", "Civic", "Honda", 2019, 30000, 18000, CarType.CONVERTIBLE));
        dealership.addCar(new Car("1FA6P8TH4J5111111", "Mustang", "Ford", 2021, 5000, 35000, CarType.COUPE));
        dealership.addCar(new Car("1GNEK13ZX3R123456", "Tahoe", "Chevrolet", 2018, 40000, 45000, CarType.SUV));
        dealership.addCar(new Car("2C4RC1BG5HR123456", "Pacifica", "Chrysler", 2020, 20000, 28000, CarType.HATCHBACK));
        dealership.addCar(new Car("1N4AL3AP4JC123456", "Altima", "Nissan", 2019, 25000, 19000, CarType.ELECTRIC));
        dealership.addCar(new Car("5NMS3CAD0LH123456", "Santa Fe", "Hyundai", 2021, 10000, 32000, CarType.SUV));
        dealership.addCar(new Car("3N1AB7APXKY123456", "Sentra", "Nissan", 2020, 15000, 17000, CarType.SEDAN));

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Выберите действие:");
            System.out.println("1.Добавить машину в автоцентр");
            System.out.println("2. Найти все машины указанного производителя");
            System.out.println("3. Получить среднюю цену машин определённого типа");
            System.out.println("4. Получить список машин, отсортированных по году выпуска");
            System.out.println("5. Получить количество машин каждого типа");
            System.out.println("6. Найти самую старую и самую новую машины в наличии");
            System.out.println("0. Выход");

            System.out.print("Ваш выбор: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Добавление автомобиля
                    System.out.print("Введите VIN: ");
                    String vin = scanner.nextLine();
                    System.out.print("Введите модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Введите производителя: ");
                    String manufacturer = scanner.nextLine();
                    System.out.print("Введите год выпуска: ");
                    int year = scanner.nextInt();
                    System.out.print("Введите пробег: ");
                    int mileage = scanner.nextInt();
                    System.out.print("Введите цену: ");
                    double price = scanner.nextDouble();
                    System.out.print("Введите тип (SEDAN, SUV, COUPE, VAN, TRUCK): ");
                    CarType type = CarType.valueOf(scanner.next().toUpperCase());

                    Car car = new Car(vin, model, manufacturer, year, mileage, price, type);
                    if (dealership.addCar(car)) {
                        System.out.println("Автомобиль успешно добавлен.");
                    } else {
                        System.out.println("Автомобиль с таким VIN уже существует.");
                    }
                    break;
                case 2:
                    // Поиск автомобилей по производителю
                    System.out.print("Введите имя производителя: ");
                    String searchManufacturer = scanner.nextLine();
                    List<Car> foundCars = dealership.findCarsByManufacturer(searchManufacturer);
                    if (foundCars.isEmpty()) {
                        System.out.println("Автомобили не найдены.");
                    } else {
                        foundCars.forEach(c -> System.out.println(c));
                    }
                    break;

                case 3:
                    // Средняя цена автомобилей определенного типа
                    System.out.print("Введите тип (SEDAN, SUV, COUPE, VAN, TRUCK): ");
                    CarType avgType = CarType.valueOf(scanner.next().toUpperCase());
                    double averagePrice = dealership.averagePriceByType(avgType);
                    System.out.println("Средняя цена для типа " + avgType + ": " + averagePrice);
                    break;
                case 4:
                    // Список машин, отсортированных по году выпуска
                    List<Car> sortedCars = dealership.getCarsSortedByYear();
                    sortedCars.forEach(c -> System.out.println(c));
                    break;
                case 5:
                    // Количество машин по типу
                    Map<CarType, Long> countByType = dealership.getCountCarsByType();
                    countByType.forEach((typeKey, count) ->System.out.println(typeKey + ": " + count));
                    break;
                case 6:
                    // Самая старая и самая новая машина
                    Map<String, Car> oldestAndNewest = dealership.findOldestAndNewestCar();
                    System.out.println("Самая старая: " + oldestAndNewest.get("самая старая"));
                    System.out.println("Самая новая: " + oldestAndNewest.get("самая новая"));
                    break;
                case 0:
                    // Выход
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }

        } while (choice != 0);

        scanner.close();
    }

}
