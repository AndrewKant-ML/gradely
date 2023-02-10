package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.title.Title;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

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

    public Student getStudentByUser(User user) throws DAOException, UserNotFoundException, UnrecognizedRoleException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        for (Student s : students) {
            if (s.getUser().equals(user)) {
                return s;
            }
        }
        try {
            Student daoStudent = DAOFactoryAbstract.getInstance().getStudentDAO().getStudentByUser(user);
            students.add(daoStudent);
            return daoStudent;
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public Student newStudent(User user, String matricola, List<Title> titles) throws DAOException, MissingAuthorizationException {
        Student student = new Student(user, matricola);
        user.setRole(student);
        if (titles != null){
            student.setTitles(titles);
        }
        try {
            DAOFactoryAbstract.getInstance().getStudentDAO().insert(student);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        students.add(student);
        return student;
    }
}
