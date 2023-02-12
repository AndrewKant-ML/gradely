package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;

import java.util.List;

public class StudentGradeListBean {
    private List<StudentGradeBean> grades;
    private ExamBean exam;

    public StudentGradeListBean(List<StudentGradeBean> grades, ExamBean exam){
        this.grades = grades;
        this.exam = exam;
        // TBI implement exam check on list
    }

    public List<StudentGradeBean> getGrades(){
        return grades;
    }

    public void setGrades(List<StudentGradeBean> grades){
        this.grades = grades;
    }

    public ExamBean getExam(){
        return exam;
    }

    public void setExam(ExamBean exam){
        this.exam = exam;
    }
}
