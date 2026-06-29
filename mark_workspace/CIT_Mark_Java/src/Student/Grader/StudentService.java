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

    public void saveToDatabase() throws SQLException {
        String insertStudentQuery =
                "INSERT INTO STUDENTS (name, registration_number, sst, math, sci, eng) VALUES (?, ?, ?, ?, ?, ?) " +
                        "ON CONFLICT (registration_number) DO UPDATE SET " +
                        "sst = EXCLUDED.sst, " +
                        "math = EXCLUDED.math, " +
                        "sci = EXCLUDED.sci, " +
                        "eng = EXCLUDED.eng, " +
                        "name = EXCLUDED.name";
        PreparedStatement preparedStatement = connection.prepareStatement(insertStudentQuery);
        for(String registrationNumber : studentHashMap.keySet()){
            preparedStatement.setString(1, studentHashMap.get(registrationNumber).getName());
            preparedStatement.setString(2, registrationNumber);
            preparedStatement.setInt(3,studentHashMap.get(registrationNumber).getScoresList()[0]);
            preparedStatement.setInt(4,studentHashMap.get(registrationNumber).getScoresList()[1]);
            preparedStatement.setInt(5,studentHashMap.get(registrationNumber).getScoresList()[2]);
            preparedStatement.setInt(6,studentHashMap.get(registrationNumber).getScoresList()[3]);
            preparedStatement.execute();
        }
    }
    public void list(String registrationNumber){
        System.out.println(studentHashMap.get(registrationNumber));
    }
}