package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamResultBean;

public class StudentGradeBean {
    private ExamEnrollmentBean enrollmentBean;
    private ExamResultBean examResultBean;

    public StudentGradeBean(ExamEnrollmentBean enrollmentBean, ExamResultBean examResultBean){
        this.enrollmentBean = enrollmentBean;
        this.examResultBean = examResultBean;
    }

    public ExamEnrollmentBean getEnrollmentBean(){
        return enrollmentBean;
    }

    public void setEnrollmentBean(ExamEnrollmentBean enrollmentBean){
        this.enrollmentBean = enrollmentBean;
    }

    public ExamResultBean getExamResultBean(){
        return examResultBean;
    }

    public void setExamResultBean(ExamResultBean examResultBean){
        this.examResultBean = examResultBean;
    }
}
