package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProfessorDAODB extends DAODBAbstract<Professor> implements AbstractProfessorDAO {
    protected static AbstractProfessorDAO instance;

    private ProfessorDAODB(){

    }

    public static synchronized AbstractProfessorDAO getInstance() {
        if (instance == null) {
            instance = new ProfessorDAODB();
        }
        return instance;
    }

    @Override
    public Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException {
        String query = "select matricola, dipartimento from PROFESSOR P where codice_fiscale='%s';";
        query = String.format(query, user.getCodiceFiscale());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    Professor professor = new Professor(
                            user,
                            rs.getString("matricola"),
                            DipartimentoEnum.getDipartimentoByValue(rs.getInt("dipartimento"))
                    );
                    setProfessorData(professor);
                    return professor;
                } else
                    throw new UserNotFoundException(ExceptionMessagesEnum.PROFESSOR_NOT_FOUND.message);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public void setProfessorData(Professor professor) throws DAOException, UserNotFoundException {
        try {
            professor.setCoordinatedCourse(DegreeCourseLazyFactory.getInstance().getDegreeCourseByCoordinatore(professor));
        } catch (ObjectNotFoundException e) {
            professor.setCoordinatedCourse(null);
        }
        try {
            professor.setCourseAssignments(SubjectCourseAssignmentLazyFactory.getInstance().getCourseAssignmentsByProfessor(professor));
        } catch (ObjectNotFoundException e) {
            professor.setCourseAssignments(null);
        }
    }
    @Override
    public void insert(Professor professor) throws DAOException {

    }

    @Override
    public void cancel(Professor professor) throws DAOException {

    }

    @Override
    public void update(Professor professor) throws DAOException {

    }

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, Professor professor) throws SQLException {

    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, Professor professor) throws SQLException, MissingAuthorizationException {

    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException {

    }

}
