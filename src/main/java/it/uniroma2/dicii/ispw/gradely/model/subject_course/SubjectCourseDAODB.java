package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.io.IOException;
import java.sql.*;
import java.time.Year;
import java.util.List;
import java.util.logging.Level;


public class SubjectCourseDAODB extends SubjectCourseDAOAbstract {

    public SubjectCourseDAODB() {
        super();
    }

    public static synchronized SubjectCourseDAOAbstract getInstance() {
        if (instance == null) {
            instance = new SubjectCourseDAODB();
        }
        return instance;
    }

    /**
     * Retrieves data of a single SubjectCourse, executing a given query
     *
     * @param query the query to be executed
     * @return a SubjectCourse object
     * @throws ObjectNotFoundException   thrown if the query produces no results
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException         thrown if errors occur while loading db connection properties
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    private SubjectCourse querySingleSubjectCourseData(String query) throws ObjectNotFoundException, DAOException, PropertyException, ResourceNotFoundException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    return new SubjectCourse(
                            SubjectCourseCodeEnum.getSubjectCourseCodeByValue(rs.getInt("code")),
                            rs.getString("name"),
                            Year.of(rs.getDate("aa").toLocalDate().getYear()),
                            rs.getInt("cfu")
                    );
                } else
                    throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Retrieve a SubjectCourse from given code, name, cfu and academic year
     *
     * @param name         the SubjectCourse's name
     * @param code         the SubjectCourse's code
     * @param cfu          the SubjectCourse's cfu
     * @param academicYear the SubjectCourse's academic year
     * @return a SubjectCourse object
     * @throws ObjectNotFoundException   thrown if the query produces no results
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException         thrown if errors occur while loading db connection properties
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    @Override
    public SubjectCourse getSubjectCourseByNameCodeCfuAndAcademicYear(String name, SubjectCourseCodeEnum code, Integer cfu, Year academicYear) throws ObjectNotFoundException, DAOException, PropertyException, ResourceNotFoundException {
        String query = "select * from SUBJECT_COURSE SC where SC.code=%d and SC.name='%s' and SC.cfu=%d and SC.aa=%d;";
        query = String.format(query, code.value, name, cfu, academicYear.getValue());
        return querySingleSubjectCourseData(query);
    }

    @Override
    public void insert(SubjectCourse subjectCourse) throws PropertyException, ResourceNotFoundException, DAOException {
        insertQuery("SUBJECT_COURSE", List.of("code", "name", "cfu", "aa"),subjectCourse);

    }

    @Override
    public void cancel(SubjectCourse subjectCourse) throws PropertyException, ResourceNotFoundException, DAOException {
        cancelQuery("SUBJECT_COURSE",List.of("code", "name", "cfu", "aa"), List.of(subjectCourse.getCode().toString(),subjectCourse.getName(),subjectCourse.getCfu().toString(),subjectCourse.getAcademicYear().toString()));
    }

    @Override
    public void update(SubjectCourse subjectCourse) throws DAOException, PropertyException, ResourceNotFoundException {
        //TODO can't update because all primary keys
        insert(subjectCourse);
    }

    void setQueryParameters(PreparedStatement stmt, SubjectCourse subjectCourse) throws SQLException {
        stmt.setInt(1,subjectCourse.getCode().value);
        stmt.setString(2, subjectCourse.getName());
        stmt.setInt(3, subjectCourse.getCfu());
        stmt.setDate(4, Date.valueOf(subjectCourse.getAcademicYear().atDay(0))); //TODO fix year
    }


}
