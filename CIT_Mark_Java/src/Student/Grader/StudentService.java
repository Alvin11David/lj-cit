package Student.Grader;
import java.util.HashMap;
import java.util.Arrays;

public class StudentService {
    HashMap<String,Student> studentHashMap = new HashMap<>();

    public void add(String registrationNumber, Student student){
        studentHashMap.put(registrationNumber,student);
    }


}