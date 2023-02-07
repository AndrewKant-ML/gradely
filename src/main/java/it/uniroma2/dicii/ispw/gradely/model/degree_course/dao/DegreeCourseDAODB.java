package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.*;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.AbstractDegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DegreeCourseDAODB extends AbstractDegreeCourseDAO {

    private DegreeCourseDAODB() {

    }

    public static synchronized AbstractDegreeCourseDAO getInstance() {
        if (instance == null) {
            instance = new DegreeCourseDAODB();
        }
        return instance;
    }

    /**
     * Retrieves DegreeCourse data by executing a given query
     *
     * @param query the query to be executed
     * @return a DegreeCourse object
     * @throws ObjectNotFoundException thrown if the DegreeCourse cannot be found
     * @throws DAOException            thrown if errors occur while retrieving data from persistence layer
     */
    private DegreeCourse querySingleDegreeCourseData(String query) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    int code = rs.getInt("code");
                    DegreeCourse degreeCourse = new DegreeCourse(DegreeCourseCodeEnum.getDegreeCourseCodeByValue(code), rs.getString("name"), FacoltaEnum.getFacoltaByValue(rs.getInt("facolta")), DipartimentoEnum.getDipartimentoByValue(rs.getInt("dipartimento")), DegreeCourseTypeEnum.getDegreeCourseTypeByValue(rs.getInt("type")), TestTypeEnum.getTestTypeByValue(rs.getInt("test_type")));
                    degreeCourse.setPrerequisites(DegreeCourseLazyFactory.getInstance().getDegreeCourseByDegreeCourseCodeList(getPrerequisitesCodesByDegreeCourseCode(code)));
                    return degreeCourse;
                } else throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Retrieves DegreeCourse data by executing a given query
     *
     * @param query the query to be executed
     * @return a DegreeCourse object
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    private List<DegreeCourse> queryMultipleDegreeCourseData(String query) throws DAOException, PropertyException, ResourceNotFoundException {
        List<DegreeCourse> degreeCourses = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    degreeCourses.add(new DegreeCourse(
                            DegreeCourseCodeEnum.getDegreeCourseCodeByValue(rs.getInt("code")),
                            rs.getString("name"),
                            FacoltaEnum.getFacoltaByValue(rs.getInt("facolta")),
                            DipartimentoEnum.getDipartimentoByValue(rs.getInt("dipartimento")),
                            DegreeCourseTypeEnum.getDegreeCourseTypeByValue(rs.getInt("type")),
                            TestTypeEnum.getTestTypeByValue(rs.getInt("test_type"))
                    ));
                }
                return degreeCourses;
            } catch (SQLException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Queries the DB to find a degree course by its name
     *
     * @param name the name of the degree course to be found
     * @return an instance of DegreeCourse corresponding to the one with the given name
     * @throws DAOException thrown if error occurs while executing DB operations
     */
    @Override
    public DegreeCourse getDegreeCourseByName(String name) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException {
        String query = "select DC.code as code,name,facolta,dipartimento,type,test_type from DEGREE_COURSE DC join ABSTRACT_DEGREE_COURSE ADC on DC.code=ADC.code where DC.name='%s';";
        query = String.format(query, name);
        return querySingleDegreeCourseData(query);
    }

    /**
     * Retrieves all the DegreeCourse objects not in a given list
     *
     * @param degreeCourses the DegreeCourse objects which shall not be returned
     * @return a list of DegreeCourse objects
     * @throws DAOException thrown if error occurs while executing DB operations
     */
    @Override
    public List<DegreeCourse> getAllDegreeCourses(List<DegreeCourse> degreeCourses) throws DAOException, PropertyException, ResourceNotFoundException {
        String query = "select DC.code as code,name,facolta,dipartimento,type,test_type from DEGREE_COURSE DC join ABSTRACT_DEGREE_COURSE ADC on DC.code = ADC.code";
        if (!degreeCourses.isEmpty()) {
            query = query.concat(" where ADC.code not in (%s);");
            StringBuilder builder = new StringBuilder();
            for (DegreeCourse degreeCourse : degreeCourses)
                builder.append(degreeCourse.getCode().value).append(",");
            builder.deleteCharAt(builder.length() - 1);
            query = String.format(query, builder);
        }
        return queryMultipleDegreeCourseData(query);
    }

    @Override
    public DegreeCourse getDegreeCourseByCoordinatore(Professor professor) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException {
        String query = "select DC.code as code,name,facolta,dipartimento,type,test_type from DEGREE_COURSE DC join ABSTRACT_DEGREE_COURSE ADC on DC.code=ADC.code where DC.coordinatore='%s';";
        query = String.format(query, professor.getUser().getCodiceFiscale());
        return querySingleDegreeCourseData(query);
    }

    /**
     * Builds a list of DegreeCourseCodeEnum corresponding to the prerequisites
     *
     * @param code the code of the DegreeCourse whose prerequisites have to be found
     * @return a list of DegreeCourseCodeEnum
     * @throws DAOException thrown if error occurs while executing DB operations
     */
    private List<DegreeCourseCodeEnum> getPrerequisitesCodesByDegreeCourseCode(int code) throws DAOException, PropertyException, ResourceNotFoundException {
        String query = "select abstract_degree_course as code from DEGREE_COURSE_PREREQUISITE where degree_course_code=%d;"; //TODO fix degree_course_code column name
        query = String.format(query, code);
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                List<DegreeCourseCodeEnum> codes = new ArrayList<>();
                while (rs.next())
                    codes.add(DegreeCourseCodeEnum.getDegreeCourseCodeByValue(rs.getInt("code")));
                return codes;
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public List<AbstractDegreeCourse> getDegreeCoursesByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) throws DAOException, PropertyException, ResourceNotFoundException {
        if (codes.isEmpty())
            return new ArrayList<>();
        String query = "select DC.code as code,name,facolta,dipartimento,type,test_type from DEGREE_COURSE DC join ABSTRACT_DEGREE_COURSE ADC on DC.code = ADC.code where ADC.code in (%s);";
        StringBuilder builder = new StringBuilder();
        for (DegreeCourseCodeEnum code : codes)
            builder.append(code.value + ',');
        builder.deleteCharAt(builder.length() - 1);
        query = String.format(query, builder);
        return new ArrayList<>(queryMultipleDegreeCourseData(query));
    }

    @Override
    public void insert(DegreeCourse degreeCourse) {

    }

    @Override
    public void cancel(DegreeCourse degreeCourse) {

    }

    @Override
    public void update(DegreeCourse degreeCourse) {

    }

    @Override
    public List<DegreeCourse> refresh(List<DegreeCourse> degreeCourses) {
        return null;
    }
}
