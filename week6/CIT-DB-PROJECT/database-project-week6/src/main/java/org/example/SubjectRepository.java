package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository implements CRUDRepository<Subject,Integer> {

    @Override
    public void save(Subject subject) {
        String sql = "INSERT INTO subject (name) VALUES (?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1,subject.getName());
            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Subject findById(Integer integer) {
        String sql = "SELECT subject_id,name FROM subject WHERE subject_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1,integer);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new Subject(rs.getString("name"));
            }

        } catch( SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Subject> loadAll() {
        List<Subject> subjects = new ArrayList<>();

        String sql = "SELECT * FROM subject";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                subjects.add(new Subject(rs.getString("name")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return subjects;
    }
}
