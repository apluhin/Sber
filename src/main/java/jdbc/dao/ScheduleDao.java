package jdbc.dao;


import jdbc.entity.Schedule;

import java.util.List;

public interface ScheduleDao {
    void save(Schedule schedule);

    List<Schedule> getAll();
}
