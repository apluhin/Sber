package jdbc.dao.impl;

import jdbc.dao.Search;
import jdbc.entity.Schedule;
import jdbc.entity.Student;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class SearchImpl implements Search {

    private final Connection connection;

    public SearchImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Student> findBySchedule(Schedule schedule) {
        ResultSet execute = execute("SELECT * FROM Student" +
                " WHERE id in " +
                "(SELECT student_id FROM Visit WHERE schedule_id = ?)", (s) -> s.setLong(1, schedule.getId()));
        List<Student> list = new ArrayList<>();
        try {
            while (execute.next()) {
                list.add(new Student(execute.getInt(1), execute.getString(2), execute.getString(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error during query", e);
        }

        return list;
    }

    @Override
    public List<Schedule> findByStudent(Student student) {
        ResultSet execute = execute("SELECT * FROM Schedule" +
                " WHERE id in " +
                "(SELECT schedule_id FROM Visit WHERE student_id = ?)", (s) -> s.setLong(1, student.getId()));
        List<Schedule> list = new ArrayList<>();
        try {
            while (execute.next()) {
                list.add(new Schedule(execute.getInt(1), parse(execute.getTimestamp(2)), execute.getInt(3), execute.getString(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error during query", e);
        }
        return list;
    }

    private LocalDateTime parse(Timestamp timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp.getTime(), 0, ZoneOffset.UTC);
    }

    private ResultSet execute(String query, Consumer<PreparedStatement> consumer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            consumer.accept(preparedStatement);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException("Error during query", e);
        }
    }

    private interface Consumer<T> {
        void accept(T t) throws Exception;
    }
}
