package org.example.task4;

public class Person {
    @JsonField(name = "first_name")
    private String firstName;

    @JsonField(name = "age")
    private int age;

    private String ignoredField; // Не сериализуется

    public Person(String firstName, int age, String ignoredField) {
        this.firstName = firstName;
        this.age = age;
        this.ignoredField = ignoredField;
    }
}
