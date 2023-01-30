package it.uniroma2.dicii.ispw.gradely.model.timers;

import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;

import java.time.LocalDate;

public class ExamConfirmationTimer extends AbstractTimer{
    private Exam exam;

    public ExamConfirmationTimer(Exam exam, LocalDate expiration) {
        super(expiration);
        this.exam = exam;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
}
