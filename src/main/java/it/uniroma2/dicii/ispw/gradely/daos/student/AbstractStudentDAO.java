package it.uniroma2.dicii.ispw.gradely.daos.student;

import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.User;

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
