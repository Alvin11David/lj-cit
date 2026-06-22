package week5;
import java.util.Map;
import java.util.HashMap;


public class Main3 {
    public static void main(String[] args){
        Map<String,Integer> scores = new HashMap<>();
        scores.put("Mark",83);
        scores.put("Tagoole",95);
        scores.put("Alvin",86);
        scores.put("Larrie",95);
        scores.put("Fidel",99);

        System.out.println(scores.get("Mark"));

        scores.put("Alvin",99);
        System.out.println(scores.get("Alvin"));

        System.out.println("Map Size: " + scores.size());
    }

}