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
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse degreeCourse) throws DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        String query = "select * from DEGREE_COURSE_ENROLLMENT DCE join STUDENT S on DCE.student=S.codice_fiscale join USER U on S.codice_fiscale = U.codice_fiscale where DCE.degree_course_code=%d and DCE.degree_course_name='%s';";
        query = String.format(query, degreeCourse.getCode().value, degreeCourse.getName());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                List<DegreeCourseEnrollment> enrollments = new ArrayList<>();
                Student student = null;
                while (rs.next()) {
                    student = StudentLazyFactory.getInstance().getStudentByUser(UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString("codice_fiscale")));
                    enrollments.add(new DegreeCourseEnrollment(rs.getDate("enrollment_date").toLocalDate(), student, degreeCourse));
                }
                return enrollments;
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Retrieves all the DegreeCourseEnrollments of a given Student
     *
     * @param student the Student whose DegreeCourseEnrollments have to be found
     * @return a List of DegreeCourseEnrollments objects
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) throws DAOException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException {
        String query = "select degree_course_name as name, enrollment_date from DEGREE_COURSE_ENROLLMENT DCE where DCE.student='%s'";
        query = String.format(query, student.getCodiceFiscale());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                List<DegreeCourseEnrollment> enrollments = new ArrayList<>();
                DegreeCourse degreeCourse;
                while (rs.next()) {
                    degreeCourse = DegreeCourseLazyFactory.getInstance().getDegreeCourseByName(rs.getString("name"));
                    enrollments.add(new DegreeCourseEnrollment(rs.getDate("enrollment_date").toLocalDate(), student, degreeCourse));
                }
                return enrollments;
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        } catch (ObjectNotFoundException e) { // TODO handle this
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(DegreeCourseEnrollment degreeCourseEnrollment){

    }

    @Override
    public void cancel(DegreeCourseEnrollment degreeCourseEnrollment){

    }

    @Override
    public void update(DegreeCourseEnrollment degreeCourseEnrollment){

    }

    @Override
    protected DegreeCourseEnrollment queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(DegreeCourseEnrollment degreeCourseEnrollment, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }


}