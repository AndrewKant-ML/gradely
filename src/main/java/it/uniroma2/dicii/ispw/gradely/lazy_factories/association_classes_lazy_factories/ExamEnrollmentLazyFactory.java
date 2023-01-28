package it.uniroma2.dicii.ispw.gradely.lazy_factories.association_classes_lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.factories.DAOFactory;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.ExamEnrollment;

import java.util.ArrayList;
import java.util.List;

public class ExamEnrollmentLazyFactory {
    private static ExamEnrollmentLazyFactory instance;
    private List<ExamEnrollment> examEnrollments;

    private ExamEnrollmentLazyFactory(){
        examEnrollments = new ArrayList<ExamEnrollment>();
    }

    public static ExamEnrollmentLazyFactory getInstance(){
        if (instance == null) {
            instance = new ExamEnrollmentLazyFactory();
        }
        return instance;
    }

    public List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam) {
        List<ExamEnrollment> list = new ArrayList<>();
        for(ExamEnrollment e : examEnrollments){
            if(e.getExam().equals(exam)) {
                list.add(e); //TODO implementare exception
            }
        }
        List<ExamEnrollment> daoList = DAOFactory.getDAOFactory().getExamEnrollmentDAO().getExamEnrollmentsByExam(exam); //TODO implementare exception
        for(ExamEnrollment e : daoList){
            if(!list.contains(e)) {
                list.add(e); //TODO implementare exceptions
            }
        }
        return list;
    }

    public List<ExamEnrollment> getExamEnrollmentsByStudent(Student student) {
        List<ExamEnrollment> list = new ArrayList<>();
        for(ExamEnrollment e : examEnrollments){
            if(e.getStudent().equals(student)) {
                list.add(e); //TODO implementare exception
            }
        }
        List<ExamEnrollment> daoList = DAOFactory.getDAOFactory().getExamEnrollmentDAO().getExamEnrollmentsByStudent(student); //TODO implementare exception
        for(ExamEnrollment e : daoList){
            if(!list.contains(e)) {
                list.add(e); //TODO implementare exceptions
            }
        }
        return list;
    }

    public List<ExamEnrollment> getExamEnrollmentsByExamAndStudent(Exam exam, Student student) {
        List<ExamEnrollment> list = new ArrayList<>();
        for(ExamEnrollment e : examEnrollments){
            if(e.getExam().equals(exam) && e.getStudent().equals(student)) {
                list.add(e); //TODO implementare exception
            }
        }
        List<ExamEnrollment> daoList = DAOFactory.getDAOFactory().getExamEnrollmentDAO().getExamEnrollmentsByExam(exam); //TODO implementare exception
        for(ExamEnrollment e : daoList){
            if(!list.contains(e)) {
                list.add(e); //TODO implementare exceptions
            }
        }
        return list;
    }

    public void saveExamResult(ExamEnrollment enrollment, ExamResult result){
        enrollment.setExamResult(result);
        DAOFactory.getDAOFactory().getExamEnrollmentDAO().update(enrollment);
    }
}