package week4.exercise1;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("John Walker", 32000, 24, "Beverages");
        Product product2 = new Product("LV", 45000, 33, "Clothes");
        Product product3 = new Product("Nike", 760000, 67, "Shoes");

        product1.displayInfo();
        System.out.println("In Stock: " + product1.isInStock());
        System.out.println(product1.restock(10));

        System.out.println();
        product2.displayInfo();
        System.out.println("In Stock: " + product2.isInStock());
        System.out.println(product2.restock(10));

        System.out.println();
        product3.displayInfo();
        System.out.println("In Stock: " + product3.isInStock());
        System.out.println(product3.restock(10));
    }
}
