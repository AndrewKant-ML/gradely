package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

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
    public void delete(ExamEnrollment examEnrollment){

    }

    @Override
    public void update(ExamEnrollment examEnrollment){
        System.out.println("Updated");
    }


}