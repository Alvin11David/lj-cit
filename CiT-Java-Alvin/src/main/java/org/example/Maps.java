package org.example;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    public static void main(String[] args) {
        Map<String, Integer> balances = new HashMap<>();
        balances.put("Alvin", 4000);
        balances.put("David", 3000);

        int alvinBalance = balances.get("Alvin");
        System.out.println(alvinBalance);
        boolean hasDavid = balances.containsKey("David");
        System.out.println(hasDavid);

        balances.put("Fidel", 2000);
        System.out.println(balances);
    }
}
