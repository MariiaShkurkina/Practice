package org.example.task2;

import java.time.Year;
import java.util.Random;

public class Arr {
    public static void main(String[] args){
        int size=50;
        int[] arrCar=new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arrCar[i] = random.nextInt(26) + 2000;
        }
        System.out.println("Все машины");
        for (int num : arrCar) {
            System.out.println(num);
        }
        System.out.println("Машины, выпущенные после 2015 года");
        for (int num : arrCar) {
            if (num>2015){
            System.out.println(num);}
        }
        int currentYear= Year.now().getValue();
        System.out.println("Текущий год: "+currentYear);
        int meanAge=0;
        for (int num : arrCar) {
            meanAge+=(currentYear-num);
        }
        meanAge=meanAge/size;
        System.out.println("Средний возраст авто: "+meanAge);

    }
}

