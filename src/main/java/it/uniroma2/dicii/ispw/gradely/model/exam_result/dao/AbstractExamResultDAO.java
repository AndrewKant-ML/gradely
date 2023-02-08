package it.uniroma2.dicii.ispw.gradely.model.exam_result.dao;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;

public abstract class AbstractExamResultDAO implements DAODBAbstract<ExamResult> {
    protected static AbstractExamResultDAO instance;

    protected AbstractExamResultDAO(){
    }


}
