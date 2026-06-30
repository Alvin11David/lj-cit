package org.example;

public class Product {
    String name;
    double price;
    int stockQuantity;
    String category;


    public Product(String name, double price, int stockQuantity, String category) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }


    void displayInfo() {
        System.out.println("Product: " + name + " | Price: UGX " + price + " | Stock: " + stockQuantity + " | Category: " + category);
    }

    void restock(int units) {
        stockQuantity += units;
        System.out.println("Restocked " + name + " by " + units + " units " + "New stack: " + stockQuantity);
    }

    boolean isInStock() {
        return  stockQuantity > 0;
    }

    public static void main(String[] args) {
        Product product1 = new Product("Laptop", 100000.0, 100, "electronic");
        Product product2 = new Product("Shirt", 20000.0, 100, "Clothing");

        product1.displayInfo();
        product1.restock(5);
        System.out.println("InStock: " + product1.isInStock());
        System.out.println();

        product2.displayInfo();
        product2.restock(10);
        System.out.println("Instock: " + product2.isInStock());
        System.out.println();
    }
}