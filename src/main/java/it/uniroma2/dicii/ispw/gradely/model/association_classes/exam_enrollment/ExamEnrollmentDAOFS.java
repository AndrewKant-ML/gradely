package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.ArrayList;
import java.util.List;

public class ExamEnrollmentDAOFS implements ExamEnrollmentDAOInterface {

    private static ExamEnrollmentDAOFS instance;
    private ExamEnrollmentDAOFS(){

    }

    public static synchronized ExamEnrollmentDAOInterface getInstance(){
        if (instance == null){
            instance = new ExamEnrollmentDAOFS();
        }
        return instance;
    }

    @Override
    public List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam, List<ExamEnrollment> exclusions) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return new ArrayList<>();
    }

    @Override
    public List<ExamEnrollment> getExamEnrollmentsByStudent(Student student, List<ExamEnrollment> exclusions) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return new ArrayList<>();
    }

    @Override
    public ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student){
        return null;
    }

    @Override
    public void insert(ExamEnrollment examEnrollment){
        // To be implemented
    }

    @Override
    public void delete(ExamEnrollment examEnrollment){
        // To be implemented
    }

    @Override
    public void update(ExamEnrollment examEnrollment){
        // To be implemented
    }


}