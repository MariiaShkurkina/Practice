package org.example.task2;

import java.util.*;

public class Collection {
    public static void main(String[] args) {
        List<String> carModels = new ArrayList<>();

        carModels.add("Toyota Camry");
        carModels.add("Honda Accord");
        carModels.add("Ford Mustang");
        carModels.add("BMW X5");
        carModels.add("Mercedes-Benz C-Class");
        carModels.add("Audi A4");
        carModels.add("BMW X5");
        carModels.add("BMW X5");
        carModels.add("Toyota Camry");
        carModels.add("Hyundai Sonata");
        carModels.add("Tesla Semi");

        //Удаление дубликатов
        Set<String> uniqueCarModels = new HashSet<>(carModels);

        //Сортировка в обратном алфавитном порядке
        List<String> uniqueCar = new ArrayList<>(uniqueCarModels);
        uniqueCar.sort(Collections.reverseOrder());
        System.out.println("Список уникальных моделей в обратном алфовитном порядке:");
        System.out.println(uniqueCar);

        //Сохранение в Set
        Set<String> uniqueCarModelsSet = new HashSet<>(uniqueCar);

        //Замена Tesla на ELECTRO_CAR
        List<String> updateList=new ArrayList<>();
        for (String model:uniqueCarModelsSet){
            if(model.contains("Tesla")){
                updateList.add("ELECTRO-CAR");
            }
            else updateList.add(model);
        }
        System.out.println("Список после замены Tesla на ELECTRO_CAR: ");
        System.out.println(updateList);


    }
}
