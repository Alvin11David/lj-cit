package week4.exercise1;

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

    public void displayInfo() {
        System.out.println("Product: " + name + " Price : " + price + " Stock: " + stockQuantity + " Category: " + category);
    }

    public String restock(int units) {
        stockQuantity += units;
        return "Restocked " + name + " by " + units + " units." + "New stock: " + stockQuantity;
    }

    public boolean isInStock() {
        return stockQuantity > 0;

    }
}
