package org.example.task4;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        //задани 1
        Printable printable = () -> System.out.println("Печать через лямбда-выражение");
        printable.print();

        //задание 2
        Predicate<String> isNotNull = s -> s!= null;
        Predicate<String> isNotEmpty = s -> !s.isEmpty();
        Predicate<String> isValidString = isNotNull.and(isNotEmpty);

        String test1 = "Hello";
        String test2 = "";
        String test3 = null;

        System.out.println(isValidString.test(test1)); // true
        System.out.println(isValidString.test(test2)); // false
        System.out.println(isValidString.test(test3)); // false

        //задание 3
        Predicate<String> isValid = s -> s != null && (s.startsWith("J") || s.startsWith("N")) && s.endsWith("A");

        String[] testStrings = {"Java", "Nokia", "NhjjA", "JlkjA", "Alpha", "null", null};
        for (String str : testStrings) {
            System.out.println("Строка: " + str + " → " + isValid.test(str));
        }

        //задание 4
        HeavyBox box = new HeavyBox(25);

        Consumer<HeavyBox> shipment = b ->
                System.out.println("Отгрузили ящик с весом " + b.getWeight());

        Consumer<HeavyBox> delivery = b ->
                System.out.println("Отправляем ящик с весом " + b.getWeight());

        Consumer<HeavyBox> action = shipment.andThen(delivery);

        action.accept(box);


        //задание 5

        Function<Integer, String> checkNumber = n -> {
            if (n > 0) return "Положительное число";
            else if (n < 0) return "Отрицательное число";
            else return "Ноль";
        };

        System.out.println(checkNumber.apply(10));   // Положительное число
        System.out.println(checkNumber.apply(-5));   // Отрицательное число
        System.out.println(checkNumber.apply(0));    // Ноль

        //задание 6
        Supplier<Integer> randomSupplier = () -> (int)(Math.random() * 10);

        System.out.println("Случайное число: " + randomSupplier.get());

        //задание 7
        DeprecatedExProcessor.processAnnotations(OldService.class);
        //задание 8
        Person person = new Person("Alice", 30, "secret");
        String json = JsonSerializer.toJson(person);
        System.out.println(json);

    }
}
