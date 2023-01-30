package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;

import java.util.List;

public abstract class AbstractExamEnrollmentDAO implements DAOInterface<ExamEnrollment> {
    protected static AbstractExamEnrollmentDAO instance;

    protected AbstractExamEnrollmentDAO() {
    }

    public abstract List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam);

    public abstract List<ExamEnrollment> getExamEnrollmentsByStudent(Student student);

    public abstract ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student);

}