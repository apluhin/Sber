package jdbc.dao;


import jdbc.entity.Schedule;
import jdbc.entity.Student;

import java.util.List;

public interface Search {
    List<Student> findBySchedule(Schedule schedule);

    List<Schedule> findByStudent(Student student);

}
