package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;

public class StudentDAODB extends AbstractStudentDAO {

    private StudentDAODB() {
    }

    public static synchronized AbstractStudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAODB();
        }
        return instance;
    }

    /**
     * Retrieve all Student's data of a given User
     *
     * @param user the User whose Student's data have to be retrieved
     * @return a Student object
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException thrown if the given User has not a Student role
     */
    @Override
    public Student getStudentByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException {
        String query = "select matricola from STUDENT where codice_fiscale='%s';";
        query = String.format(query, user.getCodiceFiscale());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    Student student = new Student(user, rs.getString("matricola"));
                    student.setDegreeCourseEnrollments(DegreeCourseEnrollmentLazyFactory.getInstance().getDegreeCourseEnrollmentsByStudent(student));
                    student.setTitles(TitleLazyFactory.getInstance().getTitlesByStudent(student));
                    student.setExamEnrollments(ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentsByStudent(student));
                    student.setSubjectCourseEnrollments(SubjectCourseEnrollmentLazyFactory.getInstance().getSubjectCourseEnrollmentsByStudent(student));
                    return student;
                } else
                    throw new UserNotFoundException(ExceptionMessagesEnum.STUDENT_NOT_FOUND.message);
            } catch (PropertyException | ResourceNotFoundException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(Student student) throws DAOException, PropertyException, ResourceNotFoundException {
        insertQuery("STUDENT", List.of("codice_fiscale","matricola"),student);
    }

    @Override
    public void cancel(Student student) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery("STUDENT",List.of("codice_fiscale"), List.of(student.getUser().getCodiceFiscale()),student);
        /*String query = "delete from USER where codice_fiscale = ?";
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1,user.getCodiceFiscale());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }*/
    }

    @Override
    public void update(Student student) throws PropertyException, ResourceNotFoundException, DAOException {
        updateQuery("STUDENT", List.of("matricola"), List.of(student.getMatricola()), List.of("codice_fiscale"), List.of(student.getUser().getCodiceFiscale()),student);
    }

    void setQueryParameters(PreparedStatement stmt, Student student) throws SQLException {
        stmt.setString(1,student.getUser().getCodiceFiscale());
        stmt.setString(2, student.getMatricola());
    }



}
