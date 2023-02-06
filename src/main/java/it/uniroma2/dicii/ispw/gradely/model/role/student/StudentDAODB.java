package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAODB extends AbstractStudentDAO {

    public StudentDAODB(){
    }

    public static synchronized AbstractStudentDAO getInstance(){
        if (instance == null){
            instance = new StudentDAODB();
        }
        return instance;
    }

    @Override
    public Student getStudentByUser(User user) throws DAOException {
        String query = "select matricola from STUDENT where codice_fiscale='%s';";
        query = String.format(query, user.getEmail());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    Student student = new Student(user, rs.getString("matricola"));
                    student.setDegreeCourseEnrollments(DegreeCourseEnrollmentLazyFactory.getInstance().getDegreeCourseEnrollmentsByStudent(student));
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return null; 
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
