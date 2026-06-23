package Student.Grader;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Optional;

public class StudentService {
    HashMap<String,Student> studentHashMap = new HashMap<>();

    public void add(String registrationNumber, Student student){
        studentHashMap.put(registrationNumber,student);
    }

    public String find(String registrationNumber){
        if(registrationNumber==null || !studentHashMap.containsKey(registrationNumber)){
            return "Student Registration Number Not Found!!. Please Try again...";
        }
        return studentHashMap.get(registrationNumber).toString();
    }

    public void list(){
        for(String registrationNumber: studentHashMap.keySet()){
            System.out.println(studentHashMap.get(registrationNumber));
        }
    }

    public Optional<Student> getStudent(String registrationNumber) {
        if (registrationNumber == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(studentHashMap.get(registrationNumber));
    }



}