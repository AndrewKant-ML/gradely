package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.sql.*;
import java.util.List;

public class StudentDAODB extends DAODBAbstract<Student> implements StudentDAOInterface {
    protected static StudentDAOInterface instance;

    private StudentDAODB() {
    }

    public static synchronized StudentDAOInterface getInstance() {
        if (instance == null) {
            instance = new StudentDAODB();
        }
        return instance;
    }

    /**
     * Returns the Student object correlated a given User
     *
     * @param user the User whose Student's data have to be retrieved
     * @return a Student object
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException thrown if the given User has not a Student role
     */
    @Override
    public Student getStudentByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException {
        return getQuery("STUDENT", List.of("matricola"), List.of("codice_fiscale"), List.of(user.getCodiceFiscale()), List.of(user));
    }

    @Override
    public void insert(Student student) throws DAOException, PropertyException, ResourceNotFoundException {
        insertQuery("STUDENT", List.of("codice_fiscale","matricola"),student);
    }

    @Override
    public void cancel(Student student) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery("STUDENT",List.of("codice_fiscale"), List.of(student.getUser().getCodiceFiscale()));
    }

    @Override
    public void update(Student student) throws PropertyException, ResourceNotFoundException, DAOException {
        updateQuery("STUDENT", List.of("matricola"), List.of("codice_fiscale"), List.of(student.getUser().getCodiceFiscale()),student);
    }

    protected void setInsertQueryParametersValue(PreparedStatement stmt, Student student) throws SQLException {
        stmt.setString(1,student.getUser().getCodiceFiscale());
        stmt.setString(2, student.getMatricola());
    }

    protected void setUpdateQueryParametersValue(PreparedStatement stmt, Student student) throws SQLException{
        stmt.setString(1, student.getMatricola());
    }

    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> values) throws SQLException{
        stmt.setString(1, (String) values.get(0));
    }
    protected Student getQueryObjectBuilder(ResultSet rs, List<Object> users) throws SQLException, DAOException, PropertyException, ResourceNotFoundException {
        Student student = new Student((User)users.get(0), rs.getString("matricola"));
        student.setDegreeCourseEnrollments(DegreeCourseEnrollmentLazyFactory.getInstance().getDegreeCourseEnrollmentsByStudent(student));
        student.setTitles(TitleLazyFactory.getInstance().getTitlesByStudent(student));
        student.setExamEnrollments(ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentsByStudent(student));
        student.setSubjectCourseEnrollments(SubjectCourseEnrollmentLazyFactory.getInstance().getSubjectCourseEnrollmentsByStudent(student));
        return student;
    }


}
