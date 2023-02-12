package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
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

<<<<<<< HEAD

    public void update (ExamResult examResult) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        try {
=======
    public void update (ExamResult examResult) throws DAOException {
        /*try {
>>>>>>> db31041 (  (dom 12 feb 2023, 12:39:59, CET))
            DAOFactoryAbstract.getInstance().getExamResultDAO().update(examResult);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }
}
