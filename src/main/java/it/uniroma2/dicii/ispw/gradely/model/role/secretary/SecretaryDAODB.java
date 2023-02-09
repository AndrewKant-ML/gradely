package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecretaryDAODB  extends DAODBAbstract<Secretary> implements AbstractSecretaryDAO {
    protected static AbstractSecretaryDAO instance;

    private SecretaryDAODB() {

    }

    public static synchronized AbstractSecretaryDAO getInstance() {
        if (instance == null) {
            instance = new SecretaryDAODB();
        }
        return instance;
    }

    /**
     * Retrieve all Secretary's data of a given User
     *
     * @param user the User whose Secretary's data have to be retrieved
     * @return a Secretary object
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException thrown if the given User has not a Student role
     */
    @Override
    public Secretary getSecretaryByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException {
        String query = "select * from SECRETARY S where S.codice_fiscale='%s';";
        query = String.format(query, user.getCodiceFiscale());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    return new Secretary(
                            user,
                            DipartimentoEnum.getDipartimentoByValue(rs.getInt("dipartimento"))
                    );
                } else
                    throw new UserNotFoundException(ExceptionMessagesEnum.SECRETARY_NOT_FOUND.message);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Gets all the Secretaries of a given dipartimento
     *
     * @param dipartimento  the dipartimento whose secretaries have to be found
     * @param excludedList the secretaries already loaded in memory
     * @return a List of Secretary objects
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException thrown if the given User has not a Secretary role
     */
    @Override
    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento, List<Secretary> excludedList) throws DAOException, UserNotFoundException, MissingAuthorizationException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UnrecognizedRoleException {
        return getListQuery("SECRETARY",List.of("dipartimento"),List.of(dipartimento.value), excludedList, null);
    }

    @Override
    public void insert(Secretary secretary) throws DAOException, PropertyException, ResourceNotFoundException {
        insertQuery("SECRETARY", List.of("codice_fiscale","dipartimento"),secretary);
    }

    @Override
    public void cancel(Secretary secretary) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery("SECRETARY",List.of("codice_fiscale","dipartimento"), List.of(secretary.getUser().getCodiceFiscale(), secretary.getDipartimento().value));
    }

    @Override
    public void update(Secretary secretary) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        updateQuery("SECRETARY",List.of("dipartimento"),List.of("codice_fiscale"),List.of(secretary.getUser().getCodiceFiscale()),secretary);
    }

    protected void setInsertQueryParametersValue(PreparedStatement stmt, Secretary secretary) throws SQLException {
        stmt.setString(1,secretary.getUser().getCodiceFiscale());
        stmt.setInt(2, secretary.getDipartimento().value);
    }

    protected void setUpdateQueryParametersValue(PreparedStatement stmt, Secretary secretary) throws SQLException{
        stmt.setInt(1, secretary.getDipartimento().value);
    }
    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<Object> identifiersValues, String queryType) throws SQLException{
        stmt.setString(1, (String) identifiersValues.get(0));
    }


    protected Secretary getQueryObjectBuilder(ResultSet rs, List<Object> users) throws SQLException, DAOException, PropertyException, ResourceNotFoundException {
        Secretary secretary = new Secretary((User) users.get(0), DipartimentoEnum.getDipartimentoByValue(rs.getInt("dipartimento")));
        return secretary;
    }
    protected Secretary getListQueryObjectBuilder(ResultSet rs, List<Object> dontCare) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException, UserNotFoundException, UnrecognizedRoleException {
        User user = UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString("codice_fiscale"));
        return user.getRole().castToSecretaryRole();
    }
    protected String getListQueryIdentifierValue(Secretary secretary, int valueNumber) throws DAOException {
        if(valueNumber == 0){
            return secretary.getUser().getCodiceFiscale();
        } else throw new DAOException("wrong list query id value");
    }


}
