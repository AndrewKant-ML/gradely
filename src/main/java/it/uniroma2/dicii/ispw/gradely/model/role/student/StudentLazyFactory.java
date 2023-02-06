package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryDB;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.title.Title;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentLazyFactory {
    private static StudentLazyFactory instance;
    private final List<Student> students;

    private StudentLazyFactory(){
        students = new ArrayList<Student>();
    }

    public static synchronized StudentLazyFactory getInstance(){
        if (instance == null){
            instance = new StudentLazyFactory();
        }
        return instance;
    }

    public Student getStudentByUser(User user) throws DAOException {
        for (Student s : students) {
            if (s.getUser().equals(user)) {
                return s;
            }
        }
        try {
            return DAOFactoryDB.getInstance().getStudentDAO().getStudentByUser(user);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public Student newStudent(String name, String surname, String codiceFiscale, String email, String password, String matricola, LocalDate registrationDate, List<Title> titles) throws DAOException {
        User user = UserLazyFactory.getInstance().newUser(name, surname, codiceFiscale, email, password, registrationDate);
        Student student = new Student(user, matricola);
        user.setRole(student);
        try {
            DAOFactoryAbstract.getInstance().getStudentDAO().insert(student);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        students.add(student);
        return student;
    }
}
