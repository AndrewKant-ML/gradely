package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans;

import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;

public class ExamResultConfirmationBean {
    ExamResultConfirmationEnum decision;

    public ExamResultConfirmationBean(ExamResultConfirmationEnum decision) {
        this.decision = decision;
    }

    public ExamResultConfirmationEnum getDecision() {
        return decision;
    }

    public void setDecision(ExamResultConfirmationEnum decision) {
        this.decision = decision;
    }
}
