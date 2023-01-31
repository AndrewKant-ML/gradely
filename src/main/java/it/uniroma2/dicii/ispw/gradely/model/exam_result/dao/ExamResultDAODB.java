package it.uniroma2.dicii.ispw.gradely.model.exam_result.dao;

import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;

import java.util.List;

public class ExamResultDAODB extends AbstractExamResultDAO {

    private ExamResultDAODB(){ //TODO implementare costruttore vero

    }

    public static AbstractExamResultDAO getInstance(){
        if (instance == null) {
            instance = new ExamResultDAODB();
        }
        return instance;
    }



    @Override
    public void insert(ExamResult examResult) {

    }

    @Override
    public void cancel(ExamResult examResult) {

    }

    @Override
    public void update(ExamResult examResult) {

    }

    @Override
    public List<ExamResult> refresh(List<ExamResult> examResults) {
        return null;
    }
}
