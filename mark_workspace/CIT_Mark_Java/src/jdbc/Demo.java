package jdbc;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Demo  {
    public static void main(String[] args)throws Exception{
        String sql = "select name from students where id=1";
        String url = "jdbc:postgresql://localhost:5432/telsuko";
        String username = "postgres";
        String password = "cit2026";


        Connection connection = DriverManager.getConnection(url,username,password);
        Statement st = connection.createStatement();
        //String insertQuery = "INSERT INTO STUDENTS(name,marks) VALUES('Ian',91)";
        //st.execute(insertQuery);

        String name = "Joelex";
        int mark = 100;
        String insertQuery2 = "INSERT INTO STUDENTS(name,marks) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery2);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,mark);
        preparedStatement.execute();



        //PreparedStatement preparedStatement = connection.prepareStatement(insertQuery2);
        //preparedStatement.setString(1,"Kelly");
        //preparedStatement.setInt(2,84);
        //preparedStatement.execute();



        /*
        String updateName = "UPDATE STUDENTS SET name = ? WHERE marks = 98";
        PreparedStatement preparedStatement1 = connection.prepareStatement(updateName);
        preparedStatement1.setString(1,"Joyce");
        preparedStatement1.execute();
        */

        String deleteQuery = "DELETE FROM STUDENTS WHERE name ='Kelly'";
        Statement statement = connection.createStatement();
        statement.execute(deleteQuery);


        String getAllResultsQuery = "SELECT * FROM STUDENTS";
        ResultSet resultSet = statement.executeQuery(getAllResultsQuery);
        while (resultSet.next()){
            System.out.println("Student Name: " + resultSet.getString("name"));
        }

        //rs.next();
        //String name = rs.getString(1);
        //System.out.println(name);
        //connection.close();
    }
}
