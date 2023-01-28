package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.enums.TitleEnum;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStudentDAO {
    private static AbstractStudentDAO instance;
    private List<Student> students;

    private AbstractStudentDAO(){ //TODO implementare costruttore vero
        students = new ArrayList<Student>();
        students.add(new Student(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it"), "123456", LocalDate.now(), new ArrayList<TitleEnum>()));
    }

    public static AbstractStudentDAO getInstance(){
        if (instance == null) {
            instance = new AbstractStudentDAO();
        }
        return instance;
    }

    public Student getStudentByUser(User user) {
        for(Student s : students){
            if(s.getUser().equals(user)) {
                return s; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }
}
