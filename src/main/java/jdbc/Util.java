package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {

    public static void createTableSchedule(Connection connection) {
        try {

            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("create table Schedule (" +
                    "id int PRIMARY KEY," +
                    "timeLesson TIMESTAMP ," +
                    "room int ," +
                    "subject VARCHAR (50) " +
                    ")");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    public static void createTableStudent(Connection connection) {
        try {

            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("create table Student (" +
                    "id int PRIMARY KEY," +
                    "name VARCHAR (50) ," +
                    "lastName VARCHAR (50)" +
                    ")");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    public static void createTableVisit(Connection connection) {
        try {

            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("create table Visit (" +
                    "schedule_id int," +
                    "student_id int" +
                    ")");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
        }
    }


}
