package it.uniroma2.dicii.ispw.gradely.model.role.student.dao;

import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public abstract class AbstractStudentDAO {
    protected static AbstractStudentDAO instance;
    protected List<Student> students;

    protected AbstractStudentDAO(){

    }


    public abstract Student getStudentByUser(User user);
    public abstract void update(Student student);

    public abstract List<Student> refresh(List<Student> students);

}
