package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam){
        return null;
    }

    @Override
    public List<ExamEnrollment> getExamEnrollmentsByStudent(Student student){
        return null;
    }

    @Override
    public ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student){
        return null;
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