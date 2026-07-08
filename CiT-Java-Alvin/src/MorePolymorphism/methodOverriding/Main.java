package MorePolymorphism.methodOverriding;

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Dog myDog = new Dog();

        myAnimal.sound();
        myDog.sound();
    }
}
