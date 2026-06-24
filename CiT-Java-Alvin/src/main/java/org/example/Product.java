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

    Product(String name, double price) {
        this(name, 0,0, "Save");
    }

    void displayInfo() {
        System.out.println("Name: " + name + "Price: " + price + "Stock Quantity: " + stockQuantity + "Category: " + category);
    }

    void restock(int units) {
        stockQuantity += units;
        System.out.println("Stock Quantity: " + stockQuantity);
    }

     boolean isInStock() {
        return stockQuantity > 0;
    }

    public static void main(String[] args) {
        Product product1 = new Product("Juice", 23000, 34, "Beverages");
        Product product2 = new Product("Samz", 2000, 500, "Biscuits");
        Product product3 = new Product("Apple", 1500, 400, "Fruits");
        Product product4 = new Product("Teju", 0);

        product1.displayInfo();
        product2.displayInfo();
        product3.displayInfo();
        product4.displayInfo();

        product1.restock(5);
        product2.restock(5);
        product3.restock(5);
        product4.restock(20);

        System.out.println("In Stock: " + product1.isInStock());
        System.out.println("In Stock: " + product2.isInStock());
        System.out.println("In Stock: " + product3.isInStock());
    }
}