package it.uniroma2.dicii.ispw.gradely.model.role.student.dao;

import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class StudentDAODB extends AbstractStudentDAO {

    public StudentDAODB(){
    }

    public static AbstractStudentDAO getInstance(){
        if (instance == null) {
            instance = new StudentDAODB();
        }
        return instance;
    }

    @Override
    public Student getStudentByUser(User user) {
        return null; //TODO implementare exceptions
    }

    @Override
    public void insert(Student student) {

    }

    @Override
    public void cancel(Student student) {

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public List<Student> refresh(List<Student> students) {
        return null;
    }
}
