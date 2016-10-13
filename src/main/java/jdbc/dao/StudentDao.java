package jdbc.dao;


import jdbc.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student student);

    List<Student> getAll();
}
