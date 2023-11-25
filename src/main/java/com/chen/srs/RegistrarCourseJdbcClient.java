package com.chen.srs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrarCourseJdbcClient {

    public static void main(String[] args) {
        Connection connection = null;
        try {
       
        	String url = "jdbc:h2:~/Downloads/temp-784/H2_784_DB;DB_CLOSE_DELAY=-1";
            String username = "sa";
            String password = "sa";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        String courseId = "2";
        String query = "SELECT COUNT(COURSE_ID) FROM REGISTRAR WHERE COURSE_ID = ?";
        int registeredStudents = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, courseId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    registeredStudents = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        System.out.println("Number of students registered for course " + courseId + ": " + registeredStudents);

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
