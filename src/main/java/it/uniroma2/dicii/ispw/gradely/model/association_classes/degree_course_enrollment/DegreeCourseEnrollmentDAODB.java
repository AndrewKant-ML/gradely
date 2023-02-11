package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DegreeCourseEnrollmentDAODB extends DAODBAbstract<DegreeCourseEnrollment> implements DegreeCourseEnrollmentDAOInterface {
    protected static DegreeCourseEnrollmentDAOInterface instance;

    private DegreeCourseEnrollmentDAODB(){

    }

    public static synchronized DegreeCourseEnrollmentDAOInterface getInstance(){
        if (instance == null){
            instance = new DegreeCourseEnrollmentDAODB();
        }
        return instance;
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse degreeCourse, List<DegreeCourseEnrollment> excluded) throws DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return getListQuery(
                "DEGREE_COURSE_ENROLLMENT",
                List.of("degree_course_name"),
                List.of(degreeCourse.getName()),
                excluded,
                List.of(degreeCourse),
                false
        );
    }

    /**
     * Retrieves all the DegreeCourseEnrollments of a given Student
     *
     * @param student the Student whose DegreeCourseEnrollments have to be found
     * @return a List of DegreeCourseEnrollments objects
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student, List<DegreeCourseEnrollment> exclusions) throws DAOException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException {
        return getListQuery(
                "DEGREE_COURSE_ENROLLMENT",
                List.of("student"),
                List.of(student.getCodiceFiscale()),
                exclusions,
                List.of(student),
                false
        );
    }

    @Override
    public void insert(DegreeCourseEnrollment degreeCourseEnrollment) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery(
                "DEGREE_COURSE_ENROLLMENT",
                List.of(degreeCourseEnrollment.getStudent().getCodiceFiscale(),degreeCourseEnrollment.getDegreeCourse().getCode().value,degreeCourseEnrollment.getDegreeCourse().getName())
        );
    }

    @Override
    public void cancel(DegreeCourseEnrollment degreeCourseEnrollment) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery(
                "DEGREE_COURSE_ENROLLMENT",
                List.of("student","degree_course_code","degree_course_name"),
                List.of(degreeCourseEnrollment.getStudent().getCodiceFiscale(),String.valueOf(degreeCourseEnrollment.getDegreeCourse().getCode().value),degreeCourseEnrollment.getDegreeCourse().getName())
        );
    }

    @Override
    public void update(DegreeCourseEnrollment degreeCourseEnrollment) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        updateQuery(
                "DEGREE_COURSE_ENROLLMENT",
                List.of("enrollment_date"),
                List.of(degreeCourseEnrollment.getEnrollmentDate()),
                List.of("student","degree_course_code","degree_course_name"),
                List.of(degreeCourseEnrollment.getStudent().getCodiceFiscale(),String.valueOf(degreeCourseEnrollment.getDegreeCourse().getCode().value),degreeCourseEnrollment.getDegreeCourse().getName())
        );
    }

    @Override
    protected DegreeCourseEnrollment queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException, WrongListQueryIdentifierValue {
        if(objects.get(0) instanceof Student)
            return new DegreeCourseEnrollment(
                    rs.getDate("enrollment_date").toLocalDate(),
                    (Student) objects.get(0),
                    DegreeCourseLazyFactory.getInstance().getDegreeCourseByName(rs.getString("degree_course_name"))
            );
        else return new
                DegreeCourseEnrollment(
                rs.getDate("enrollment_date").toLocalDate(),
                StudentLazyFactory.getInstance().getStudentByUser(UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString("student"))),
                (DegreeCourse) objects.get(0)
                );

    }

    @Override
    protected String setGetListQueryIdentifiersValue(DegreeCourseEnrollment degreeCourseEnrollment, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        if(valueNumber==0)
            return degreeCourseEnrollment.getStudent().getCodiceFiscale();
        else throw new WrongListQueryIdentifierValue(ExceptionMessagesEnum.WRONG_LIST_QUERY_IDENTIFIER_VALUE.message);
    }


}