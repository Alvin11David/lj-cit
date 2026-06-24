package org.example;

public class House {
    String name;
    int rooms;
    String type;

    void sell(String name) {
        System.out.println(name + "has been sold out");
    }

    void printSummary() {
        System.out.println("House " + name + " of rooms " + rooms + " and type " + type);
    }

    public House(String name, int rooms, String type) {
        this.name = name;
        this.rooms = rooms;
        this.type = type;
    }

    public static void main(String[] args) {
        House house = new House("SkyScrapper", 50, "Apartment");

        house.sell("SkyScrapper");
        house.printSummary();
    }

}