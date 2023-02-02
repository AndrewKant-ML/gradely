package it.uniroma2.dicii.ispw.gradely.model.role.student.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class StudentDAOFS extends AbstractStudentDAO {

    private StudentDAOFS(){ 

    }

    public static synchronized AbstractStudentDAO getInstance(){
        if (instance == null){
            instance = new StudentDAOFS();
        }
        return instance;
    }

    @Override
    public Student getStudentByUser(User user) throws DAOException {
        return null; 
    }

    @Override
    public void insert(Student student){

    }

    @Override
    public void cancel(Student student){

    }

    @Override
    public void update(Student student){

    }

    @Override
    public List<Student> refresh(List<Student> students){
        return null;
    }
}
