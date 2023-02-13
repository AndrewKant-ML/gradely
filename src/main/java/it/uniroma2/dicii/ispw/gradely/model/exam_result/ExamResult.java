package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ResultEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;

public class ExamResult {
    private Integer grade;
    private ResultEnum result;
    private ExamResultConfirmationEnum confirmed;
    private ExamEnrollment enrollment;

    public ExamResult(Integer grade, ResultEnum result, ExamResultConfirmationEnum confirmed, ExamEnrollment enrollment) {
        this.grade = grade;
        this.result = result;
        this.confirmed = confirmed;
        this.enrollment = enrollment;
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

    public ExamEnrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(ExamEnrollment enrollment) {
        this.enrollment = enrollment;
    }
}
