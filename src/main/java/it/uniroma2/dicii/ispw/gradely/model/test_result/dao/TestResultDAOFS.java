package it.uniroma2.dicii.ispw.gradely.model.test_result.dao;

import it.uniroma2.dicii.ispw.gradely.model.test_result.AbstractTestResult;

import java.util.List;

public class TestResultDAOFS extends TestResultDAOAbstract {

    private TestResultDAOFS(){
        super();
    }

    public static synchronized TestResultDAOAbstract getInstance(){
        if (instance == null){
            instance = new TestResultDAOFS();
        }
        return instance;
    }

    @Override
    public void insert(AbstractTestResult abstractTestResult){

    }

    @Override
    public void cancel(AbstractTestResult abstractTestResult){

    }

    @Override
    public void update(AbstractTestResult abstractTestResult){

    }

}
