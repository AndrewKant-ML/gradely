package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ResultEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;

import java.util.ArrayList;
import java.util.List;

public class ExamResultLazyFactory {
    private static ExamResultLazyFactory instance;
    private List<ExamResult> factoryObjects;

    private ExamResultLazyFactory(){
        factoryObjects = new ArrayList<>();
    }

    public static synchronized ExamResultLazyFactory getInstance(){
        if (instance == null){
            instance = new ExamResultLazyFactory();
        }
        return instance;
    }

    public ExamResult newExamResult(Integer grade, ResultEnum result, ExamResultConfirmationEnum confirmed, ExamEnrollment enrollment){
        ExamResult e = new ExamResult(grade, result, confirmed, enrollment);
        factoryObjects.add(e);
        return e;
    }

    public void update (ExamResult examResult) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
         try {
            DAOFactoryAbstract.getInstance().getExamResultDAO().update(examResult);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }
}
