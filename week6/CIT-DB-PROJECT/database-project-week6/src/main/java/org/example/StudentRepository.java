package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements CRUDRepository<Student,String>{
    @Override
    public void save(Student student) {
        String sql ="INSERT INTO student VALUES(regno,name) (??)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1,student.getRegNo());
            ps.setString(2,student.getName());
            ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(String s) {
        String sql = "SELECT student_id,regno,name FROM student WHERE regno = ?";

        try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)
    ){
        ps.setString(1, s);
        ResultSet rs = ps.executeQuery();


        if (rs.next()){
            String regNo = rs.getString("regno");
            String name = rs.getString("name");
            return new Student(regNo,name);
        }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }




    @Override
    public List<Student> loadAll() {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM student";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String regNo = rs.getString("regno");
                String name = rs.getString("name");
                students.add(new Student(regNo,name));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }
}
