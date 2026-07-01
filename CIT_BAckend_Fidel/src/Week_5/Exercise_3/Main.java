package Week_5.Exercise_3;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        Map<String, Integer> scores = new HashMap<>();


        scores.put("Otieno", 85);
        scores.put("Fidel", 91);
        scores.put("Alvin", 98);
        scores.put("Joe", 75);
        scores.put("Eron", 88);

        System.out.println(" Class Register Initialized ");


        String studentToLookup = "Fidel";
        if (scores.containsKey(studentToLookup)) {
            Integer fidelScore = scores.get(studentToLookup);
            System.out.println(studentToLookup + " exam score is: " + fidelScore );
        }


        System.out.println("\nUpdating Alvin's score...");
        System.out.println("Old Score: " + scores.get("Alvin"));

        scores.put("Alvin", 83);

        System.out.println("New Score: " + scores.get("Alvin"));


        int totalStudents = scores.size();
        System.out.println("\nTotal number of students in the register: " + totalStudents);


        System.out.println("\nFull Register: " + scores);
    }
}