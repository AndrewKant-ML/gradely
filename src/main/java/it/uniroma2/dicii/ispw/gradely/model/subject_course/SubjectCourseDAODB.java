package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;


public class SubjectCourseDAODB extends DAODBAbstract<SubjectCourse> implements SubjectCourseDAOInterface {
    protected static SubjectCourseDAOInterface instance;

    public SubjectCourseDAODB() {
        super();
    }

    public static synchronized SubjectCourseDAOInterface getInstance() {
        if (instance == null) {
            instance = new SubjectCourseDAODB();
        }
        return instance;
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
     * @throws PropertyException thrown if errors occur while loading db connection properties OR thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    @Override
    public SubjectCourse getSubjectCourseByNameCodeCfuAndAcademicYear(String name, SubjectCourseCodeEnum code, Integer cfu, Year academicYear) throws ObjectNotFoundException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, UnrecognizedRoleException {
        return getQuery("SUBJECT_COURSE",List.of("code","name","cfu","aa"),List.of("code","name","cfu","aa"),List.of(String.valueOf(code.value),name,String.valueOf(cfu),academicYear.toString()),null);
        //mancano attributi secondari prereq,degreecourse, assignments,exams,enrollments
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

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, SubjectCourse subjectCourse) throws SQLException {
        stmt.setInt(1,subjectCourse.getCode().value);
        stmt.setString(2, subjectCourse.getName());
        stmt.setInt(3, subjectCourse.getCfu());
        stmt.setDate(4, Date.valueOf(subjectCourse.getAcademicYear().atDay(0))); //TODO fix year
    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, SubjectCourse subjectCourse) throws SQLException, MissingAuthorizationException {
        setInsertQueryParametersValue(stmt,subjectCourse);
    }


    @Override
    protected SubjectCourse queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, MissingAuthorizationException, UnrecognizedRoleException {
        return new SubjectCourse(SubjectCourseCodeEnum.getSubjectCourseCodeByValue(rs.getInt("code")),
                rs.getString("name"),
                Year.of(rs.getDate("aa").toLocalDate().getYear()),
                rs.getInt("cfu")
                );
    }

    @Override
    protected String setGetListQueryIdentifiersValue(SubjectCourse subjectCourse, int valueNumber) throws DAOException {
        return null;
    }


}
