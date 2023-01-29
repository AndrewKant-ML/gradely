package it.uniroma2.dicii.ispw.gradely.daos.student;

import it.uniroma2.dicii.ispw.gradely.enums.TitleEnum;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOFS extends AbstractStudentDAO {

    private StudentDAOFS(){ //TODO implementare costruttore vero
        students = new ArrayList<Student>();
        students.add(new Student(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it"), "123456", LocalDate.now(), new ArrayList<TitleEnum>()));
    }

    public static AbstractStudentDAO getInstance(){
        if (instance == null) {
            instance = new StudentDAOFS();
        }
        return instance;
    }

    @Override
    public Student getStudentByUser(User user) {
        for(Student s : students){
            if(s.getUser().equals(user)) {
                return s; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public List<Student> refresh(List<Student> students) {
        return null;
    }
}