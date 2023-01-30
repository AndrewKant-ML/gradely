package it.uniroma2.dicii.ispw.gradely.model.role.student.dao;

import it.uniroma2.dicii.ispw.gradely.enums.TitleEnum;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOFS extends AbstractStudentDAO {

    private StudentDAOFS(){ //TODO implementare costruttore vero

    }

    public static AbstractStudentDAO getInstance(){
        if (instance == null) {
            instance = new StudentDAOFS();
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
    public void update(Student student) {

    }

    @Override
    public List<Student> refresh(List<Student> students) {
        return null;
    }
}