package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;

public class ExamResultDAOFS implements ExamResultDAOInterface {

    private static ExamResultDAOFS instance;

    private ExamResultDAOFS(){ 

    }

    public static synchronized ExamResultDAOInterface getInstance(){
        if (instance == null){
            instance = new ExamResultDAOFS();
        }
        return instance;
    }

    public void insert(ExamResult examResult) throws DAOException {

    }

    public void cancel(ExamResult examResult) throws DAOException {

    }

    public void update(ExamResult examResult) throws DAOException {

    }

}
