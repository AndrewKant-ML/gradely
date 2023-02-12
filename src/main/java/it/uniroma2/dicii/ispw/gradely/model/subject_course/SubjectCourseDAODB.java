package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;


public class SubjectCourseDAODB extends DAODBAbstract<SubjectCourse> implements SubjectCourseDAOInterface {
    private static final String SUBJECT_COURSE ="SUBJECT_COURSE";


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
    public SubjectCourse getSubjectCourseByNameCodeCfuAndAcademicYear(String name, SubjectCourseCodeEnum code, Integer cfu, Year academicYear) throws ObjectNotFoundException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return getQuery(
                SUBJECT_COURSE,
                List.of("code","name","cfu","aa"),
                List.of(code.value,name,cfu,Date.valueOf(academicYear.atDay(1))),
                null
        );
        //mancano attributi secondari prereq,degreecourse, assignments,exams,enrollments
    }

    @Override
    public void insert(SubjectCourse subjectCourse) throws PropertyException, ResourceNotFoundException, DAOException, MissingAuthorizationException {
        insertQuery(
                SUBJECT_COURSE,
                List.of(subjectCourse.getCode().value,subjectCourse.getName(),subjectCourse.getCfu(),Date.valueOf(subjectCourse.getAcademicYear().atDay(0)))
        );

    }

    @Override
    public void delete(SubjectCourse subjectCourse) throws PropertyException, ResourceNotFoundException, DAOException {
        deleteQuery(
                SUBJECT_COURSE,
                List.of("code", "name", "cfu", "aa"),
                List.of(subjectCourse.getCode().value,subjectCourse.getName(),subjectCourse.getCfu(),Date.valueOf(subjectCourse.getAcademicYear().atDay(0)))
        );
    }

    @Override
    public void update(SubjectCourse subjectCourse) throws DAOException, PropertyException, ResourceNotFoundException {
        throw new DAOException("SubjectCourse is an non updatable resource");
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
