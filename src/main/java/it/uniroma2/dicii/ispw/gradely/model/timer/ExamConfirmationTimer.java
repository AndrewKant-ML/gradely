package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesControl;

import java.time.LocalDate;

public class ExamConfirmationTimer extends AbstractTimer<Exam, InsertStudentsGradesControl>{

    protected ExamConfirmationTimer(LocalDate expiration, Exam exam){
        super(expiration, exam);
    }

    @Override
    public ExamConfirmationTimer castToExamConfirmationTimer(){
        return this;
    }
}
