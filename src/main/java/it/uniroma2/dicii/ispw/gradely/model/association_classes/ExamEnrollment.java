package it.uniroma2.dicii.ispw.gradely.model.association_classes;

import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.Student;

import java.time.LocalDate;

public class ExamEnrollment {
    private LocalDate enrollmentDate;
    private Student student;
    private Exam exam;
    private ExamResult grade;

    public ExamEnrollment(LocalDate enrollmentDate, Student student, Exam exam, ExamResult grade) {
        this.enrollmentDate = enrollmentDate;
        this.student = student;
        this.exam = exam;
        this.grade = grade;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public ExamResult getGrade() {
        return grade;
    }

    public void setGrade(ExamResult grade) {
        this.grade = grade;
    }
}
