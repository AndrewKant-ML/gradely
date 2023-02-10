package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.sql.*;
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
    public void insert(Professor professor) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery("PROFESSOR",List.of("codice_fiscale","matricola", "dipartimento"),professor);
    }

    @Override
    public void cancel(Professor professor) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery("PROFESSOR",List.of("codice_fiscale"),List.of(professor.getUser().getCodiceFiscale()));
    }

    @Override
    public void update(Professor professor) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        updateQuery("PROFESSOR",List.of("matricola", "dipartimento"),List.of("codice_fiscale"),List.of(professor.getUser().getCodiceFiscale()),professor);
    }

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, Professor professor) throws SQLException, MissingAuthorizationException {
        stmt.setString(1,professor.getUser().getCodiceFiscale());
        stmt.setString(2, professor.getMatricola());
        stmt.setInt(3, professor.getDipartimento().value);
    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, Professor professor) throws SQLException, MissingAuthorizationException {
        stmt.setString(1, professor.getMatricola());
        stmt.setInt(2, professor.getDipartimento().value);
    }

    @Override
    protected Professor queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, MissingAuthorizationException, UnrecognizedRoleException {
        return null;
    }


    @Override
    protected String setGetListQueryIdentifiersValue(Professor professor, int valueNumber) throws DAOException {
        return null;
    }

}
