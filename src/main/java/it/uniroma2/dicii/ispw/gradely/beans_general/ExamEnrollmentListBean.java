package it.uniroma2.dicii.ispw.gradely.beans_general;

import java.util.List;

public class ExamEnrollmentListBean {
    private List<ExamEnrollmentBean> examEnrollmentBeans;

    public ExamEnrollmentListBean(List<ExamEnrollmentBean> examEnrollmentBeans){
        this.examEnrollmentBeans = examEnrollmentBeans;
    }

    public List<ExamEnrollmentBean> getExamEnrollmentBeans(){
        return examEnrollmentBeans;
    }

    public void setExamEnrollmentBeans(List<ExamEnrollmentBean> examEnrollmentBeans){
        this.examEnrollmentBeans = examEnrollmentBeans;
    }
}
