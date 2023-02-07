package it.uniroma2.dicii.ispw.gradely.model.test;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.util.List;

public class TestDAOFS extends TestDAOAbstract {

    private TestDAOFS(){
        super();
    }

    public static synchronized TestDAOAbstract getInstance() {
        if (instance == null) {
            instance = new TestDAOFS();
        }
        return instance;
    }

    @Override
    void saveTestInfo(Test test) {

    }

    @Override
    Test getTestById(String id) throws PropertyException, ResourceNotFoundException, DAOException {
        return null;
    }

    @Override
    public void insert(Test test) {

    }

    @Override
    public void cancel(Test test) {

    }

    @Override
    public void update(Test test){

    }
}
