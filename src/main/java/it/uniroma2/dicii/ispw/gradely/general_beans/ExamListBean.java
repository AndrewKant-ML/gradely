package it.uniroma2.dicii.ispw.gradely.general_beans;

import it.uniroma2.dicii.ispw.gradely.model.Exam;

import java.util.ArrayList;
import java.util.List;

public class ExamListBean {
    private List<ExamBean> exams;

    public ExamListBean(List<ExamBean> exams) {
        this.exams = exams;

    }
}
