package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;

import java.util.List;

public abstract class AbstractExamEnrollmentDAO {
    protected static AbstractExamEnrollmentDAO instance;
    protected List<ExamEnrollment> examEnrollments;

    protected AbstractExamEnrollmentDAO() {
    }

    public abstract List<ExamEnrollment> getExamEnrollmentsByExam(Exam exam);

    public abstract List<ExamEnrollment> getExamEnrollmentsByStudent(Student student);

    public abstract ExamEnrollment getExamEnrollmentByExamAndStudent(Exam exam, Student student);

    public abstract void update(ExamEnrollment enrollment);

    public abstract List<ExamEnrollment> refresh(List<ExamEnrollment> examEnrollments);


}