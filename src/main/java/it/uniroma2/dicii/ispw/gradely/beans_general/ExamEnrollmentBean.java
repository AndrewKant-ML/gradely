package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

public class ExamEnrollmentBean {
    private StudentBean student;
    private ExamBean exam;

    public ExamEnrollmentBean(StudentBean student, ExamBean exam){
        this.student = student;
        this.exam = exam;
    }

    public StudentBean getStudent(){
        return student;
    }

    public void setStudent(StudentBean student){
        this.student = student;
    }

    public ExamBean getExam() {
        return exam;
    }

    public void setExam(ExamBean exam) {
        this.exam = exam;
    }

    public String getStudentMatricola() {
        return this.student.getMatricola();
    }
}
