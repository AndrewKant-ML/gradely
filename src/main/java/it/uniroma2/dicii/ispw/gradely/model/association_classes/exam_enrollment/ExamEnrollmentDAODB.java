package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExamEnrollmentDAODB extends DAODBAbstract<ExamEnrollment> implements AbstractExamEnrollmentDAO {
    protected static AbstractExamEnrollmentDAO instance;

    private ExamEnrollmentDAODB(){

    }

    public static synchronized AbstractExamEnrollmentDAO getInstance(){
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
    public void update(ExamEnrollment enrollment){
        System.out.println("Updated");
    }

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, ExamEnrollment examEnrollment) throws SQLException {

    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, ExamEnrollment examEnrollment) throws SQLException, MissingAuthorizationException {

    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException {

    }


}