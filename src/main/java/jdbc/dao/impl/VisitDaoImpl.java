package jdbc.dao.impl;


import jdbc.dao.VisitDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisitDaoImpl implements VisitDao {

    private final Connection connection;

    public VisitDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(int studentId, int scheduleId) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into Visit (student_id, schedule_id) VALUES (?,?)");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, scheduleId);
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
}
