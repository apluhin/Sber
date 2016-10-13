package jdbc.dao.impl;


import jdbc.dao.StudentDao;
import jdbc.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Student student) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into Student (id, name, lastName) VALUES (?,?,?)");
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException("Error during insert", e);
        }
    }

    @Override
    public List<Student> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Student");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException("Error during get", e);
        }
    }
}
