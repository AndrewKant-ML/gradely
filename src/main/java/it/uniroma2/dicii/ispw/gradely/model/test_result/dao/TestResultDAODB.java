package it.uniroma2.dicii.ispw.gradely.model.test_result.dao;

import it.uniroma2.dicii.ispw.gradely.model.test_result.AbstractTestResult;

import java.util.List;

public class TestResultDAODB extends TestResultDAOAbstract {

    private TestResultDAODB(){
        super();
    }
    public static synchronized TestResultDAOAbstract getInstance(){
        if (instance == null) {
            instance = new TestResultDAODB();
        }
        return instance;
    }

    @Override
    public void insert(AbstractTestResult abstractTestResult) {

    }

    @Override
    public void cancel(AbstractTestResult abstractTestResult) {

    }

    @Override
    public void update(AbstractTestResult abstractTestResult) {

    }

    @Override
    public List<AbstractTestResult> refresh(List<AbstractTestResult> abstractTestResults) {
        return null;
    }
}
