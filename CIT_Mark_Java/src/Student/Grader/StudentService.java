package Student.Grader;
import java.util.HashMap;

public class StudentService {
    private HashMap<String,Student> studentHashMap = new HashMap<>();

    public HashMap<String, Student> getStudentHashMap() {
        return studentHashMap;
    }

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

    public void list(String registrationNumber){
        System.out.println(studentHashMap.get(registrationNumber));
    }
}