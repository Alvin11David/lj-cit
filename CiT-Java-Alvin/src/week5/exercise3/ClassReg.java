package week5.exercise3;

import java.util.HashMap;
import java.util.Map;

public class ClassReg {
    Map<String, Integer> scores = new HashMap<>();

    public ClassReg() {
        scores.put("Alvin", 89);
        scores.put("David", 78);
        scores.put("Ian", 88);
        scores.put("Irene", 99);
        scores.put("Retha", 67);
        System.out.println(scores.get("Alvin"));
        scores.put("Alvin", 99);
        System.out.println(scores.get("Alvin"));
        System.out.println(scores.size());
    }

    public static void main(String[] args) {
        new ClassReg();
    }
}
