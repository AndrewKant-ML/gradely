package it.uniroma2.dicii.ispw.gradely.model.test_result.dao;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.test_result.AbstractTestResult;

public abstract class TestResultDAOAbstract implements DAOAbstract<AbstractTestResult> {
    protected static TestResultDAOAbstract instance;

    protected TestResultDAOAbstract(){
    }

}
