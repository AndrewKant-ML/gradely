package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.model.Exam;

import java.time.LocalDate;

public class ExamConfirmationTimer {
    private Exam exam;
    private LocalDate confirmationExpiration;

    public ExamConfirmationTimer(Exam exam, LocalDate confirmationExpiration) {
        this.exam = exam;
        this.confirmationExpiration = confirmationExpiration;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public LocalDate getConfirmationExpiration() {
        return confirmationExpiration;
    }

    public void setConfirmationExpiration(LocalDate confirmationExpiration) {
        this.confirmationExpiration = confirmationExpiration;
    }
}
