package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    protected void setInsertQueryParametersValue(PreparedStatement stmt, Test test) throws SQLException {

    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, Test test) throws SQLException, MissingAuthorizationException {

    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException {

    }

}
