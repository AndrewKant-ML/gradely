package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ResultEnum;

public class ExamResult {
    private Integer grade;
    private ResultEnum result;
    private ExamResultConfirmationEnum confirmed;

    public ExamResult(){
        this.confirmed = ExamResultConfirmationEnum.NULL;
    }
    public ExamResult(Integer grade, ResultEnum result, ExamResultConfirmationEnum confirmed){
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

    public ExamResultConfirmationEnum getConfirmed(){
        return confirmed;
    }

    public void setConfirmed(ExamResultConfirmationEnum confirmed){
        this.confirmed = confirmed;
    }
}
