package it.uniroma2.dicii.ispw.gradely.beans_general;

import java.time.LocalDate;

public class ProtocolBean {
    private ExamBean examBean;
    private Integer verbaleNumber;
    private LocalDate verbaleDate;

    public ProtocolBean(ExamBean examBean, Integer verbaleNumber, LocalDate verbaleDate){
        this.examBean = examBean;
        this.verbaleNumber = verbaleNumber;
        this.verbaleDate = verbaleDate;
    }

    public ExamBean getExamBean(){
        return examBean;
    }

    public void setExamBean(ExamBean examBean){
        this.examBean = examBean;
    }

    public Integer getVerbaleNumber(){
        return verbaleNumber;
    }

    public void setVerbaleNumber(Integer verbaleNumber){
        this.verbaleNumber = verbaleNumber;
    }

    public LocalDate getVerbaleDate(){
        return verbaleDate;
    }

    public void setVerbaleDate(LocalDate verbaleDate){
        this.verbaleDate = verbaleDate;
    }
}
