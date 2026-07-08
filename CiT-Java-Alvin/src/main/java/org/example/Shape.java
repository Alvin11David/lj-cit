package org.example;

public abstract class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }
    //Abstract method - No body - child provides one
    public abstract double area();

    public void describe() {
        System.out.println(name + " has an area of " + area());
    }
}

