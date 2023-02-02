package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.dao;

import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.List;

public class ExamEnrollmentDAOFS extends AbstractExamEnrollmentDAO {

    private ExamEnrollmentDAOFS(){

    }

    public static synchronized AbstractExamEnrollmentDAO getInstance(){
        if (instance == null){
            instance = new ExamEnrollmentDAOFS();
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
    public List<ExamEnrollment> refresh(List<ExamEnrollment> examEnrollments){
        return null;
    }

}