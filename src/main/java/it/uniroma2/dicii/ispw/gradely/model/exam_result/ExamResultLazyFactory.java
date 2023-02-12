package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;

import java.util.ArrayList;
import java.util.List;

public class ExamResultLazyFactory {
    private static ExamResultLazyFactory instance;
    private List<Exam> exams;

    private ExamResultLazyFactory(){
        exams = new ArrayList<Exam>();
    }

    public static synchronized ExamResultLazyFactory getInstance(){
        if (instance == null){
            instance = new ExamResultLazyFactory();
        }
        return instance;
    }

    public void update (ExamResult examResult) throws DAOException {
        /*try {
            DAOFactoryAbstract.getInstance().getExamResultDAO().update(examResult);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }*/
    }
}
