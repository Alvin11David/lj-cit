package Student.Grader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class StudentService {
    private String url = "jdbc:postgresql://localhost:5432/cit_student_db";
    private String username = "postgres";
    private String password = "cit2026";
    private Connection connection = DriverManager.getConnection(url,username,password);
    private HashMap<String,Student> studentHashMap = new HashMap<>();


    public StudentService() throws SQLException {
    }

    public HashMap<String, Student> getStudentHashMap() {
        return studentHashMap;
    }

    public void add(String registrationNumber, Student student) throws SQLException {
        studentHashMap.put(registrationNumber,student);
        String insertStudentQuery = "INSERT INTO STUDENTS(name,registration_number) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertStudentQuery);
        preparedStatement.setString(1,student.getName());
        preparedStatement.setString(2,student.getRegistrationNumber());
        preparedStatement.execute();
        System.out.println(student.getName() + " " + student.getRegistrationNumber());

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