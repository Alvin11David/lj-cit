package org.example;

public record Record(String name, int age, double gpa) {

    public static void main(String[] args) {
        Record record1 = new Record("Alvin", 22, 4.8);
        System.out.println(record1.name());
        System.out.println(record1.age());
        System.out.println(record1.gpa());

    }
}
