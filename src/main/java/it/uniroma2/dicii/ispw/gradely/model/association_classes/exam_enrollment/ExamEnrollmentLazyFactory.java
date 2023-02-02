package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
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

    public static synchronized ExamEnrollmentLazyFactory getInstance(){
        if (instance == null){
            instance = new ExamEnrollmentLazyFactory();
        }
        return instance;
    }

    public List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam) throws DAOException {
        List<ExamEnrollment> list = new ArrayList<>();
        for(ExamEnrollment e : examEnrollments){
            if(e.getExam().equals(exam)){
                list.add(e);
            }
        }
        List<ExamEnrollment> daoList = DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().getExamEnrollmentsByExam(exam);
        for(ExamEnrollment e : daoList){
            if(!list.contains(e)){
                list.add(e);
            }
        }
        return list;
    }

    public List<ExamEnrollment> getExamEnrollmentsByStudent(Student student) throws DAOException {
        List<ExamEnrollment> list = new ArrayList<>();
        for(ExamEnrollment e : examEnrollments){
            if(e.getStudent().equals(student)){
                list.add(e);
            }
        }
        List<ExamEnrollment> daoList = DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().getExamEnrollmentsByStudent(student);
        for(ExamEnrollment e : daoList){
            if(!list.contains(e)){
                list.add(e);
            }
        }
        return list;
    }

    public ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student) throws DAOException {
        for(ExamEnrollment e : examEnrollments){
            if(e.getExam().equals(exam) && e.getStudent().equals(student)){
                return e;
            }
        }
        return DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().getExamEnrollmentByExamAndStudent(exam, student);
    }

    public void saveExamResult(ExamEnrollment enrollment, ExamResult result) throws DAOException {
        enrollment.setExamResult(result);
        DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().update(enrollment);
    }

    public void update (ExamEnrollment exam) throws DAOException {
        DAOFactoryAbstract.getInstance().getExamEnrollmentDAO().update(exam);
    }
}