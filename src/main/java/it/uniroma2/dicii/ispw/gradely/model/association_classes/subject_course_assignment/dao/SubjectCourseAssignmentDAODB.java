package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.dao;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;


public class SubjectCourseAssignmentDAODB extends AbstractSubjectCourseAssignmentDAO {
    private SubjectCourseAssignmentDAODB() {

    }

    public static synchronized AbstractSubjectCourseAssignmentDAO getInstance() {
        if (instance == null) {
            instance = new SubjectCourseAssignmentDAODB();
        }
        return instance;
    }

    private List<SubjectCourseAssignment> queryMultipleSubjectCourseAssignmentData(String query) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, ObjectNotFoundException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                List<SubjectCourseAssignment> assignments = new ArrayList<>();
                while (rs.next()) {
                    assignments.add(new SubjectCourseAssignment(
                            SubjectCourseLazyFactory.getInstance().getSubjectCourseByCodeNameCfuAndAcademicYear(
                                    SubjectCourseCodeEnum.getSubjectCourseCodeByValue(rs.getInt("sc_code")),
                                    rs.getString("sc_name"),
                                    rs.getInt("sc_cfu"),
                                    Year.of(rs.getDate("sc_aa").toLocalDate().getYear())
                            ),
                            ProfessorLazyFactory.getInstance().getProfessorByUser(UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString("professor")))
                    ));
                }
                return assignments;
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public List<SubjectCourseAssignment> getCourseAssignmentsBySubjectCourse(SubjectCourse course) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, ObjectNotFoundException {
        String query = "select professor, sc_code, sc_name, sc_cfu, sc_aa from SUBJECT_COURSE_ASSIGNMENT SCA where SCA.sc_code=%d and SCA.sc_name='%s' and SCA.sc_cfu=%d and SCA.sc_aa=%d;";
        query = String.format(query, course.getCode().value, course.getName(), course.getCfu(), course.getAcademicYear().getValue());
        return queryMultipleSubjectCourseAssignmentData(query);
    }

    @Override
    public List<SubjectCourseAssignment> getCourseAssignmentsByProfessor(Professor professor) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, ObjectNotFoundException {
        String query = "select professor, sc_code, sc_name, sc_cfu, sc_aa from SUBJECT_COURSE_ASSIGNMENT SCA where SCA.professor='%s';";
        query = String.format(query, professor.getUser().getCodiceFiscale());
        return queryMultipleSubjectCourseAssignmentData(query);
    }

    @Override
    public void insert(SubjectCourseAssignment subjectCourseAssignment){

    }

    @Override
    public void cancel(SubjectCourseAssignment subjectCourseAssignment){

    }

    @Override
    public void update(SubjectCourseAssignment subjectCourseAssignment){

    }



}
