package org.example;

public class Getters {
    private String name;
    private int age;
    private boolean active;

    //Getter for name
    public String getName() {
        return name;
    }

    //Getter for age
    public int getAge() {
        return age;
    }

    //Getter for active
    public boolean isActive() {
        return active;
    }

    public static void main(String[] args) {
        Getters g = new Getters();

        System.out.println(g.getName());
        System.out.println(g.getAge());
    }
}
