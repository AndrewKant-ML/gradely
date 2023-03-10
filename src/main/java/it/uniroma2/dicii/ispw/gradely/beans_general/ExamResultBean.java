package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.enums.ResultEnum;

public class ExamResultBean {
    private Integer grade;
    private ResultEnum result;
    private Boolean confirmed;

    public ExamResultBean(){
    }
    public ExamResultBean(Integer grade, ResultEnum result, Boolean confirmed){
        this.grade = grade;
        this.result = result;
        this.confirmed = confirmed;
    }

    public Integer getGrade(){
        return grade;
    }

    public void setGrade(Integer grade){
        this.grade = grade;
    }

    public ResultEnum getResult(){
        return result;
    }

    public void setResult(ResultEnum result){
        this.result = result;
    }

    public Boolean getConfirmed(){
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed){
        this.confirmed = confirmed;
    }
}