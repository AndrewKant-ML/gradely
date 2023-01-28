package it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.ExamEnrollment;

import java.util.List;

public abstract class AbstractExamEnrollmentDAO {
    protected static AbstractExamEnrollmentDAO instance;
    protected AbstractExamEnrollmentDAO() { //TODO implementare costruttore vero
    }

    public abstract List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam);

    public abstract List<ExamEnrollment> getExamEnrollmentsByStudent(Student student);

    public abstract List<ExamEnrollment> getExamEnrollmentsByExamAndStudent(Exam exam, Student student);

    public abstract void update(ExamEnrollment enrollment);

}