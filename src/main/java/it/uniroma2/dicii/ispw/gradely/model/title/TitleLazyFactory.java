package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
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

    List<Title> getTitlesByStudent(Student student) throws UserNotFoundException, PropertyException, ResourceNotFoundException, DAOException{
        List<Title> list = new ArrayList<>();
        for (Title t : titles) {
            if (t.getStudent().equals(student)) {
                list.add(t);
            }
        }
        List<Title> DAOList = DAOFactoryAbstract.getInstance().getTitleDAO().getTitlesByStudent(student, list);
        titles.addAll(DAOList);
        list.addAll(DAOList);
        return list;
    }

    public Title newTitle(AbstractDegreeCourse degreeCourse, Student student, LocalDate achievementDate) throws DAOException {
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
