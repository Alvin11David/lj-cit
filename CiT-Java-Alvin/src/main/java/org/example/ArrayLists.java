package org.example;

import java.util.ArrayList;

public class ArrayLists {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();//for strings

        names.add("Alvin");
        names.add("David");
        names.add("Fidel");

        System.out.println(names);

        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        System.out.println(numbers);

        ArrayList<Double> decimal = new ArrayList<>();

        decimal.add(1.0);
        decimal.add(2.0);
        decimal.add(3.0);

        System.out.println(names.get(0));
        System.out.println(names.set(0, "Waluube"));
        System.out.println(names);

        System.out.println(names.remove(0));

        System.out.println(names.size());

        for(String name : names) {
            System.out.println(name);
        }

        if (names.contains("Fidel")) {
            System.out.println("Found Fidel");
        } else {
            System.out.println("Not found");
        }

        boolean has = names.contains("Fidel");
        System.out.println(has);

    }
}
