package it.uniroma2.dicii.ispw.gradely.model.test;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestDAODB extends TestDAOAbstract {

    private TestDAODB() {
        super();
    }

    public static synchronized TestDAOAbstract getInstance() {
        if (instance == null) {
            instance = new TestDAODB();
        }
        return instance;
    }

    /**
     * Retrieves data of a single Test, executing a given query
     *
     * @param query the query to be executed
     * @return a Test object
     * @throws ObjectNotFoundException   thrown if the query produces no results
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException         thrown if errors occur while loading db connection properties
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    private Test querySingleTestData(String query) throws DAOException, PropertyException, ResourceNotFoundException, ObjectNotFoundException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    return new Test(
                            DegreeCourseLazyFactory.getInstance().getDegreeCourseByName(rs.getString("degree_course_name")),
                            rs.getString("id"),
                            rs.getDate("test_date").toLocalDate(),
                            new URL(rs.getString("reservation_link")),
                            rs.getDate("result_date").toLocalDate(),
                            new URL(rs.getString("info_link")),
                            rs.getString("place")
                    );
                } else
                    throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
            }
        } catch (SQLException | IOException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Saves data of a given Test
     *
     * @param test the Test whose data have to be saved
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException         thrown if errors occur while loading db connection properties
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    @Override
    void saveTestInfo(Test test) throws PropertyException, ResourceNotFoundException, DAOException {
        String query = "insert into TEST (id, test_date, result_date, reservation_link, info_link, place, degree_course_code, degree_course_name) values('%s','%s','%s','%s','%s','%s',%d,'%s');";
        query = String.format(query, test.getId(), test.getTestDate().format(formatter), test.getResultsDate().format(formatter), test.getTestReservationLink().toString(), test.getInfoLink().toString(), test.getPlace(), test.getDegreeCourse().getCode().value, test.getDegreeCourse().getName());
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
    Test getTestById(String id) throws PropertyException, ResourceNotFoundException, DAOException, ObjectNotFoundException {
        String query = "select id, test_date, result_date, reservation_link, info_link, place, degree_course_name from TEST T where T.id='%s';";
        query = String.format(query, id);
        return querySingleTestData(query);
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

}