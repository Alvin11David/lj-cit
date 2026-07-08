package org.example;

import java.util.HashMap;
import java.util.Map;

public class ClassRegister {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alvin",89);
        scores.put("David", 90);
        scores.put("Fidel", 50);
        scores.put("Denis", 49);
        scores.put("Daniel", 40);

        System.out.println("Alvin's result is: " + scores.get("Alvin"));
        scores.put("Alvin", 90);
        System.out.println("Alvin's result is: " + scores.get("Alvin"));
        System.out.println(scores.size());
    }
}
