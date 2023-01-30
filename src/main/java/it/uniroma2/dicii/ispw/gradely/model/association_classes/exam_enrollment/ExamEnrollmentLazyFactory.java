package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactory;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

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

    public ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student) {
        for(ExamEnrollment e : examEnrollments){
            if(e.getExam().equals(exam) && e.getStudent().equals(student)) {
                return e; //TODO implementare exception
            }
        }
        return DAOFactory.getDAOFactory().getExamEnrollmentDAO().getExamEnrollmentByExamAndStudent(exam, student);
    }

    public void saveExamResult(ExamEnrollment enrollment, ExamResult result){
        enrollment.setExamResult(result);
        DAOFactory.getDAOFactory().getExamEnrollmentDAO().update(enrollment);
    }

    public void update (ExamEnrollment exam){
        DAOFactory.getDAOFactory().getExamEnrollmentDAO().update(exam);
    }
}