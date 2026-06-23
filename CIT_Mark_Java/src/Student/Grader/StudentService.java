package Student.Grader;
import java.util.HashMap;
import java.util.Arrays;

public class StudentService {
    HashMap<String,Student> studentHashMap = new HashMap<>();

    public void add(String registrationNumber, Student student){
        studentHashMap.put(registrationNumber,student);
    }

    public String find(String registrationNumber){
        try {
            return studentHashMap.get(registrationNumber).toString();
        }
        catch ( Exception e){
            return "Student Registration Not Found! Please Try again....";
        }
    }




}