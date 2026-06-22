package MorePolymorphism;

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Dog myDog = new Dog();
        Pig myPig = new Pig();

        myAnimal.animalSound();
        myDog.animalSound();
        myPig.animalSound();
    }
}
