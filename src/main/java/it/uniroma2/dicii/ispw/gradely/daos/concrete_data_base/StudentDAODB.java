package it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractStudentDAO;
import it.uniroma2.dicii.ispw.gradely.enums.TitleEnum;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAODB extends AbstractStudentDAO {
    private static StudentDAODB instance;
    private List<Student> students;

    private StudentDAODB(){ //TODO implementare costruttore vero
        students = new ArrayList<Student>();
        students.add(new Student(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it"), "123456", LocalDate.now(), new ArrayList<TitleEnum>()));
    }

    public static StudentDAODB getInstance(){
        if (instance == null) {
            instance = new StudentDAODB();
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
