package org.example;
import java.util.Map;
import java.util.HashMap;
public class ClassRegister {
    public static void main(String[] args){
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Kato", 85);
        scores.put("Namubiru", 92);
        scores.put("Okello", 78);
        scores.put("Akello", 95);
        scores.put("Mukasa", 88);
        System.out.println("Members Added:..");
        for (Map.Entry<String,Integer> entry: scores.entrySet()){
            System.out.println(entry.getKey() +"-->"+ entry.getValue());
        }
        System.out.println("=".repeat(5)+"Done adding students and giving them marks"+"=".repeat(5));
        System.out.println();


        String studentToFind = "Namubiru";
        Integer namubirusScore = scores.get(studentToFind);
        System.out.printf("%s's score is: %d%n", studentToFind, namubirusScore);

        System.out.println("Updating Okello's score...");
        scores.put("Okello", 84);
        System.out.printf("Okello's new score is: %d%n", scores.get("Okello"));

        System.out.printf("Total number of students in the register: %d%n", scores.size());
    }
}
