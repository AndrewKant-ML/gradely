package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.AbstractDegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TitleLazyFactory {
    private static TitleLazyFactory instance;
    private final List<Title> titles;

    private TitleLazyFactory() {
        titles = new ArrayList<Title>();
    }

    public static synchronized TitleLazyFactory getInstance() {
        if (instance == null) {
            instance = new TitleLazyFactory();
        }
        return instance;
    }

    public List<Title> getTitlesByStudent(Student student) throws DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongListQueryIdentifierValue, ObjectNotFoundException, WrongDegreeCourseCodeException {
        List<Title> list = new ArrayList<>();
        for (Title t : titles) {
            if (t.getStudent().equals(student)) {
                list.add(t);
            }
        }
        List<Title> daoList = DAOFactoryAbstract.getInstance().getTitleDAO().getTitlesByStudent(student, list);
        titles.addAll(daoList);
        list.addAll(daoList);
        return list;
    }

    public Title newTitle(AbstractDegreeCourse degreeCourse, Student student, LocalDate achievementDate) throws DAOException, MissingAuthorizationException {
        Title newTitle = new Title(degreeCourse, student, achievementDate);
        try {
            DAOFactoryAbstract.getInstance().getTitleDAO().insert(newTitle);
            titles.add(newTitle);
            return newTitle;
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }


}
