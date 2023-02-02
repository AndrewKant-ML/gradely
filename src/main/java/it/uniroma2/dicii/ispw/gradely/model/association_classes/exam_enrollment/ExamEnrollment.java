package it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.time.LocalDate;

public class ExamEnrollment {
    private LocalDate enrollmentDate;
    private Student student;
    private Exam exam;
    private ExamResult examResult;

    public ExamEnrollment(){
    }

    public ExamEnrollment(LocalDate enrollmentDate, Student student, Exam exam, ExamResult examResult){
        this.enrollmentDate = enrollmentDate;
        this.student = student;
        this.exam = exam;
        this.examResult = examResult;
    }

    public LocalDate getEnrollmentDate(){
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public Exam getExam(){
        return exam;
    }

    public void setExam(Exam exam){
        this.exam = exam;
    }

    public ExamResult getExamResult(){
        return examResult;
    }

    public void setExamResult(ExamResult examResult){
        this.examResult = examResult;
    }
}
