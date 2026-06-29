package Student.Grader;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.*;

public class Student {
    private String name;
    private String registrationNumber;
    private int[] scoresList = new int[4];

    Student( String registrationNumber,String name){
        this.registrationNumber = registrationNumber;
        this.name = name;
    }

    public void addScore(int sst, int maths,int sci , int eng){
        scoresList[0] = sst;
        scoresList[1] = maths;
        scoresList[2] = sci;
        scoresList[3] = eng;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber(){
        return registrationNumber;
    }

    public int[] getScoresList(){
        return scoresList;
    }

    @Override
    public String toString() {
        return """
           Name: %s
           Registration No: %s
           Scores: %d | %d | %d | %d
           """.formatted(name, registrationNumber, scoresList[0], scoresList[1], scoresList[2], scoresList[3]);
    }


    public void addScore(String subject, int score){
        if(subject.toLowerCase().startsWith("sst")){
            scoresList[0] = score;
        } else if (subject.toLowerCase().startsWith("maths")) {
            scoresList[1] = score;
        } else if (subject.toLowerCase().startsWith("sci")) {
            scoresList[2] = score;
        } else if (subject.toLowerCase().startsWith("eng")) {
            scoresList[3] = score;
        }
    }

    public double average(){
        return Arrays.stream(scoresList).average().orElse(0.0);
    }

    public char grade(){
        if (average()>=80){
            return 'A';
        } else if (average()>=70) {
            return 'B';
        } else if (average()>=60) {
            return 'C';
        } else if (average()>=50) {
            return 'D';
        } else if (average()>=45) {
            return 'E';
        }else {
            return 'F';
        }
    }
}