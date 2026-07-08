package org.example;

import java.util.ArrayList;
import java.util.List;

import static java.util.Locale.filter;

public class StreamAPI {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(10);
        scores.add(20);
        scores.add(30);
        scores.add(40);
        scores.add(50);



        scores.stream()// lambda expression
                .map(score -> score >= 30)
                .forEach(System.out::println);
    }
}
