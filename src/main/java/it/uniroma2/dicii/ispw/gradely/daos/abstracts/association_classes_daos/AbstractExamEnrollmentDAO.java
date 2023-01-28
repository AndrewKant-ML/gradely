package it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.ExamEnrollment;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExamEnrollmentDAO {
    private static AbstractExamEnrollmentDAO instance;
    private List<ExamEnrollment> examEnrollments;

    private AbstractExamEnrollmentDAO() { //TODO implementare costruttore vero
        examEnrollments = new ArrayList<ExamEnrollment>();
        examEnrollments.add(new ExamEnrollment());
    }

    public static AbstractExamEnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new AbstractExamEnrollmentDAO();
        }
        return instance;
    }

    public List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam) {
        List<ExamEnrollment> lazyList = new ArrayList<>();
        for(ExamEnrollment e : examEnrollments){
            if(e.getExam().equals(exam)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        return lazyList;
    }

    public List<ExamEnrollment> getExamEnrollmentsByStudent(Student student) {
        List<ExamEnrollment> lazyList = new ArrayList<>();
        for(ExamEnrollment e : examEnrollments){
            if(e.getStudent().equals(student)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        return lazyList;
    }

    public List<ExamEnrollment> getExamEnrollmentsByExamAndStudent(Exam exam, Student student) {
        List<ExamEnrollment> lazyList = new ArrayList<>();
        for(ExamEnrollment e : examEnrollments){
            if(e.getExam().equals(exam) && e.getStudent().equals(student)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        return lazyList;
    }

    public void update(ExamEnrollment enrollment){
        System.out.println("Updated");
    }

}