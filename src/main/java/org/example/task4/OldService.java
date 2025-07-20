package org.example.task4;

@DeprecatedEx(message = "Используйте NewService вместо этого класса")
public class OldService {

    @DeprecatedEx(message = "Вызовите newMethod()")
    public void oldMethod() {
        System.out.println("Старый метод");
    }

    public void currentMethod() {
        System.out.println("Актуальный метод");
    }
}
