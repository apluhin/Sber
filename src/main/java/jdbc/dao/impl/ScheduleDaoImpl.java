package jdbc.dao.impl;

import jdbc.dao.ScheduleDao;
import jdbc.entity.Schedule;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {

    private final Connection connection;

    public ScheduleDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(Schedule schedule) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into Schedule " +
                    "(id, timeLesson, room, subject) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, schedule.getId());
            preparedStatement.setTimestamp(2, parse(schedule.getTimeLesson()));
            preparedStatement.setInt(3, schedule.getRoom());
            preparedStatement.setString(4, schedule.getSubject());
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
    public List<Schedule> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Schedule");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Schedule> schedules = new ArrayList<>();
            while (resultSet.next()) {
                schedules.add(new Schedule(resultSet.getInt(1), parseToLocal(resultSet.getTimestamp(2)), resultSet.getInt(3), resultSet.getString(4)));
            }
            return schedules;
        } catch (SQLException e) {
            throw new RuntimeException("Error during get", e);
        }
    }

    private LocalDateTime parseToLocal(Timestamp time) {
        return LocalDateTime.ofEpochSecond(time.getTime(), 0, ZoneOffset.UTC);
    }

    private Timestamp parse(LocalDateTime localDateTime) {
        return new Timestamp(localDateTime.toEpochSecond(ZoneOffset.UTC));
    }
}
