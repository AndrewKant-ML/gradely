package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
                    // TODO add retrieving SubjectCourseEnrollments & ExamEnrollments?
                    return student;
                } else
                    throw new UserNotFoundException(ExceptionMessagesEnum.STUDENT_NOT_FOUND.message);
            } catch (PropertyException | ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(Student student){

    }

    @Override
    public void cancel(Student student){

    }

    @Override
    public void update(Student student){

    }

    @Override
    public List<Student> refresh(List<Student> students){
        return null;
    }
}
