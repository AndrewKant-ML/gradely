package it.uniroma2.dicii.ispw.gradely.beans_general;

import java.util.List;

public class ExamListBean {
    private List<ExamBean> exams;

    public ExamListBean(List<ExamBean> exams){
        this.exams = exams;
    }

    public List<ExamBean> getExams() {
        return exams;
    }

    public void setExams(List<ExamBean> exams) {
        this.exams = exams;
    }
}
