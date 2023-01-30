package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryDB;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class StudentLazyFactory {
    private static StudentLazyFactory instance;
    private List<Student> students;

    private StudentLazyFactory(){
        students = new ArrayList<Student>();
    }

    public static StudentLazyFactory getInstance(){
        if (instance == null) {
            instance = new StudentLazyFactory();
        }
        return instance;
    }


    public Student getStudentByUser(User user) {
        for(Student s : students){
            if(s.getUser().equals(user)) {
                return s; //TODO implementare exception
            }
        }
        return DAOFactoryDB.getDAOFactory().getStudentDAO().getStudentByUser(user); //TODO implementare exception
    }
}
