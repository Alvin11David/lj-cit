package org.example;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Sets {
    public static void main(String[] args) {
        Set<String> names = new HashSet<>();//HashSet doesnt maintain insertion order

        names.add("Alvin");
        names.add("Waluube");
        names.add("David");
        names.add("David");
        System.out.println(names);
       if (names.contains("Alvin")) {
           System.out.println("Alvin Found");
       }
       names.remove("David");
        System.out.println(names);
        System.out.println(names.size());

        for (String name : names) {
            System.out.println(name);
        }
        names.clear();
        System.out.println(names);

        Set<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        System.out.println(set);
    }
}
