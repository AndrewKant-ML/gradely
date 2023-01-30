package it.uniroma2.dicii.ispw.gradely.model.exam_result.dao;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamResultDAOFS extends AbstractExamResultDAO {

    private ExamResultDAOFS(){ //TODO implementare costruttore vero

    }

    public static AbstractExamResultDAO getInstance(){
        if (instance == null) {
            instance = new ExamResultDAOFS();
        }
        return instance;
    }


    @Override
    public void insert(ExamResult examResult) {

    }

    @Override
    public void update(ExamResult examResult) {

    }

    @Override
    public List<ExamResult> refresh(List<ExamResult> examResults) {
        return null;
    }
}
