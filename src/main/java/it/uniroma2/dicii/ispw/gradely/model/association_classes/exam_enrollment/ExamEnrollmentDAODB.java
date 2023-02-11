package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamEnrollmentDAODB extends DAODBAbstract<ExamEnrollment> implements ExamEnrollmentDAOInterface {
    protected static ExamEnrollmentDAOInterface instance;

    private ExamEnrollmentDAODB(){

    }

    public static synchronized ExamEnrollmentDAOInterface getInstance(){
        if (instance == null){
            instance = new ExamEnrollmentDAODB();
        }
        return instance;
    }

    @Override
    public List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam, List<ExamEnrollment> exclusions) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return getListQuery(
                "EXAM_ENROLLMENT",
                List.of("exam_session","exam_appello","exam_sc_code","exam_sc_name","exam_sc_cfu","exam_sc_aa"),
                List.of(exam.getSession().value,exam.getAppello().value,exam.getSubjectCourse().getCode().value,exam.getSubjectCourse().getName(),exam.getSubjectCourse().getCfu(), Date.valueOf(exam.getSubjectCourse().getAcademicYear().atDay(0))),
                exclusions,
                null,
                false
        );
    }

    @Override
    public List<ExamEnrollment> getExamEnrollmentsByStudent(Student student, List<ExamEnrollment> exclusions) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return getListQuery(
                "EXAM_ENROLLMENT",
                List.of("student"),
                List.of(student.getCodiceFiscale()),
                exclusions,
                null,
                false
        );
    }

    @Override
    public ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return getQuery(
                "EXAM_ENROLLMENT",
                List.of("student","exam_session","exam_appello","exam_sc_code","exam_sc_name","exam_sc_cfu","exam_sc_aa"),
                List.of(exam.getSession().value,exam.getAppello().value,exam.getSubjectCourse().getCode().value,exam.getSubjectCourse().getName(),exam.getSubjectCourse().getCfu(), Date.valueOf(exam.getSubjectCourse().getAcademicYear().atDay(0))),
                null
        );
    }

    @Override
    public void insert(ExamEnrollment examEnrollment){

    }

    @Override
    public void cancel(ExamEnrollment examEnrollment){

    }

    @Override
    public void update(ExamEnrollment examEnrollment){
        System.out.println("Updated");
    }

    @Override
    protected ExamEnrollment queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(ExamEnrollment examEnrollment, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }
}