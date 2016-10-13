package jdbc.dao.impl;

import jdbc.Util;
import jdbc.dao.ScheduleDao;
import jdbc.dao.Search;
import jdbc.dao.StudentDao;
import jdbc.dao.VisitDao;
import jdbc.entity.Schedule;
import jdbc.entity.Student;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.List;

public class JdbcTest {
    private Connection connection;


    @Test
    public void testInsertStudent() throws Exception {
        String url = "jdbc:h2:mem:test";
        connection = DriverManager.getConnection(url);
        Util.createTableStudent(connection);
        Student student = new Student(1, "Alex", "Pups");
        Student student1 = new Student(2, "Vytia", "Pups");
        StudentDao studentDao = new StudentDaoImpl(connection);
        studentDao.save(student);
        List<Student> all1 = studentDao.getAll();
        Assert.assertEquals(all1.get(0).getLastName(), student.getLastName());
        studentDao.save(student1);
        List<Student> all = studentDao.getAll();
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void testInsertSchedule() throws Exception {
        String url = "jdbc:h2:mem:test1";
        connection = DriverManager.getConnection(url);
        Util.createTableSchedule(connection);
        Schedule s = new Schedule(1, LocalDateTime.of(2010, 10, 12, 10, 0), 3, "java");
        ScheduleDao dao = new ScheduleDaoImpl(connection);
        dao.save(s);
        Schedule schedule = dao.getAll().get(0);
        Assert.assertEquals(schedule.getId(), s.getId());
        Assert.assertEquals(schedule.getTimeLesson(), s.getTimeLesson());
        Assert.assertEquals(schedule.getRoom(), s.getRoom());
        Assert.assertEquals(schedule.getSubject(), s.getSubject());
    }

    @Test
    public void testSearch() throws Exception {
        String url = "jdbc:h2:mem:test2";
        connection = DriverManager.getConnection(url);

        Util.createTableSchedule(connection);
        Util.createTableStudent(connection);
        Util.createTableVisit(connection);

        Schedule schedule = new Schedule(1, LocalDateTime.of(2010, 10, 12, 10, 0), 3, "java");
        Schedule schedule1 = new Schedule(2, LocalDateTime.of(2010, 10, 12, 12, 0), 3, "java1");
        Student student1 = new Student(2, "Vytia", "Pups");

        StudentDao studentDao = new StudentDaoImpl(connection);
        ScheduleDao scheduleDao = new ScheduleDaoImpl(connection);
        studentDao.save(student1);
        scheduleDao.save(schedule);
        scheduleDao.save(schedule1);

        VisitDao visitDao = new VisitDaoImpl(connection);
        visitDao.save(student1.getId(), schedule.getId());
        visitDao.save(student1.getId(), schedule1.getId());

        Search search = new SearchImpl(connection);
        List<Schedule> byStudent = search.findByStudent(student1);
        Assert.assertEquals(byStudent.size(), 2);
        Assert.assertEquals(byStudent.get(0), schedule);
        Assert.assertEquals(byStudent.get(1), schedule1);

        List<Student> bySchedule = search.findBySchedule(schedule);
        Assert.assertEquals(bySchedule.size(), 1);
        Assert.assertEquals(bySchedule.get(0), student1);

    }
}