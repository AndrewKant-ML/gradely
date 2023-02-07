package it.uniroma2.dicii.ispw.gradely.model.exam_result.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;

import java.util.List;

public class ExamResultDAODB extends AbstractExamResultDAO {

    private ExamResultDAODB(){ 

    }

    public static synchronized AbstractExamResultDAO getInstance(){
        if (instance == null){
            instance = new ExamResultDAODB();
        }
        return instance;
    }



    @Override
    public void insert(ExamResult examResult) throws DAOException {

    }

    @Override
    public void cancel(ExamResult examResult) throws DAOException {

    }

    @Override
    public void update(ExamResult examResult) throws DAOException {

    }

}
