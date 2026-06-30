package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository implements CRUDRepository<Subject,String> {

    @Override
    public void save(Subject subject) {
        String sql = "INSERT INTO subject (code,name) VALUES (? ?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1,subject.getCode());
            ps.setString(2,subject.getName());
            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Subject findById(String id) {
        String sql = "SELECT subject_id,code,name FROM subject WHERE code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1,id);

            ResultSet rs = ps.executeQuery();


            if (rs.next()){
                int subject_id = rs.getInt("subject_id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                return new Subject(subject_id, code, name);
            }

        } catch( SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Subject> loadAll() {
        List<Subject> subjects = new ArrayList<>();

        String sql = "SELECT * FROM subject ORDER BY name ASC ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int subject_id = rs.getInt("subject_id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                subjects.add(new Subject(subject_id, code, name));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return subjects;
    }

}
