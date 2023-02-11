package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestReservationDAODB extends DAODBAbstract<Test> implements TestReservationDAOAbstract {
    protected static TestReservationDAOAbstract instance;

    private TestReservationDAODB() {
        super();
    }

    public static synchronized TestReservationDAOAbstract getInstance() {
        if (instance == null) {
            instance = new TestReservationDAODB();
        }
        return instance;
    }

    /**
     * Saves a TestReservation's data
     *
     * @param testReservation the TestReservation whose data have to be saved
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading db connection properties OR thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    @Override
    public void addTestReservation(TestReservation testReservation) throws DAOException, PropertyException, ResourceNotFoundException {
        String query = "insert into TEST_RESERVATION (test, student, reservation_date) values('%s', '%s', '%s');";
        query = String.format(query, testReservation.getTestInfo().getId(), testReservation.getStudent().getCodiceFiscale(), testReservation.getReservationDate().toString());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(Test test) {

    }

    @Override
    public void cancel(Test test) {

    }

    @Override
    public void update(Test test) {

    }

    @Override
    protected Test queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(Test test, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }

}
