package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserRoleEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UnrecognizedRoleException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TitleDAODB extends TitleDAOAbstract {

    private TitleDAODB() {
        super();
    }

    @Override
    List<Title> getTitlesByStudent(Student student, List<Title> list) throws UserNotFoundException, DAOException {
        return null;
    }

    public static synchronized TitleDAOAbstract getInstance() {
        if (instance == null) {
            instance = new TitleDAODB();
        }
        return instance;
    }

    @Override
    public void insert(Title title) throws DAOException {

    }

    @Override
    public void cancel(Title title) throws DAOException {

    }

    @Override
    public void update(Title title) throws DAOException {

    }

    @Override
    public List<Title> refresh(List<Title> titles) throws DAOException {
        return null;
    }
}
