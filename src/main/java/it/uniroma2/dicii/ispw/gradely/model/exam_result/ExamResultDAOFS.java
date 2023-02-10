package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;

public class ExamResultDAOFS extends ExamResultDAOInterface {

    private ExamResultDAOFS(){ 

    }

    public static synchronized ExamResultDAOInterface getInstance(){
        if (instance == null){
            instance = new ExamResultDAOFS();
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
