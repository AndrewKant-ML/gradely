package it.uniroma2.dicii.ispw.gradely.model.test_result.dao;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.test_result.AbstractTestResult;

public abstract class TestResultDAOAbstract implements DAODBAbstract<AbstractTestResult> {
    protected static TestResultDAOAbstract instance;

    protected TestResultDAOAbstract(){
    }

}
