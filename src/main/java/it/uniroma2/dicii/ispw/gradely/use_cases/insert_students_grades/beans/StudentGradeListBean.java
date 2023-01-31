package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans;

import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;

import java.util.List;

public class StudentGradeListBean {
    private List<StudentGradeBean> grades;
    private Exam exam;

    public StudentGradeListBean(List<StudentGradeBean> grades, Exam exam) {
        this.grades = grades;
        this.exam = exam;
        //TODO implement exam check on list
    }

    public List<StudentGradeBean> getGrades() {
        return grades;
    }

    public void setGrades(List<StudentGradeBean> grades) {
        this.grades = grades;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
