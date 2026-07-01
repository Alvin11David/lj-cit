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
        String sql ="INSERT INTO student (regno,name) VALUES (?,?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
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
            int student_id = rs.getInt("student_id");
            String regNo = rs.getString("regno");
            String name = rs.getString("name");
            return new Student(student_id, regNo, name);
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
                int student_id = rs.getInt("student_id");
                String regNo = rs.getString("regno");
                String name = rs.getString("name");
                students.add(new Student(student_id, regNo, name));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    public static void printStudentNamesAndRegNosNicely(List<Student> students){
        System.out.println("\n--- All Students ---");
        System.out.printf("%-10s %-20s%n", "Reg No", "Name");
        System.out.println("--------------------");
        for (Student s : students) {
            System.out.printf("%-10s %-20s%n", s.getRegNo(), s.getName());
        }
    }
}
