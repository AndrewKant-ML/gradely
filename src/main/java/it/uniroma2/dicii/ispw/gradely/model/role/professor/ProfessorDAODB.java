package it.uniroma2.dicii.ispw.gradely.model.role.professor;

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

public class ProfessorDAODB extends AbstractProfessorDAO {

    private ProfessorDAODB(){

    }

    public static synchronized AbstractProfessorDAO getInstance() {
        if (instance == null) {
            instance = new ProfessorDAODB();
        }
        return instance;
    }

    /**
     * Retrieve all Professor's data of a given User
     *
     * @param user the User whose Professor's data have to be retrieved
     * @return a Professor object
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException thrown if the given User has not a Professor role
     */
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
                    try {
                        professor.setCoordinatedCourse(DegreeCourseLazyFactory.getInstance().getDegreeCourseByCoordinatore(professor));
                    } catch (ObjectNotFoundException e) {
                        professor.setCoordinatedCourse(null);
                    }
                    professor.setCourseAssignments(SubjectCourseAssignmentLazyFactory.getInstance().getCourseAssignmentsByProfessor(professor));
                    return professor;
                } else
                    throw new UserNotFoundException(ExceptionMessagesEnum.PROFESSOR_NOT_FOUND.message);
            }
        } catch (SQLException | IOException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
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
    public List<Professor> refresh(List<Professor> professors) throws DAOException {
        return null;
    }
}
