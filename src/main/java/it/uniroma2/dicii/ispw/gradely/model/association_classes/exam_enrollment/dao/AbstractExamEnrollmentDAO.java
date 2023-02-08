package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.dao;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.List;

public abstract class AbstractExamEnrollmentDAO implements DAODBAbstract<ExamEnrollment> {
    protected static AbstractExamEnrollmentDAO instance;

    protected AbstractExamEnrollmentDAO(){
    }

    public abstract List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam);

    public abstract List<ExamEnrollment> getExamEnrollmentsByStudent(Student student);

    public abstract ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student);

}