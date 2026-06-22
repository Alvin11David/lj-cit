package MorePolymorphism.methodOverloading;

public class Main {
    public static void main(String[] args) {
        Calculator myCalc = new Calculator();

        System.out.println(myCalc.add(20, 30));

        System.out.println(myCalc.add(20, 30, 40));

        System.out.println(myCalc.add(20.1, 30.1));
    }
}
