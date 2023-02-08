package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;

import java.util.List;

public class TestReservationDAOFS extends TestReservationDAOAbstract {

    private TestReservationDAOFS(){
        super();
    }

    public static synchronized TestReservationDAOAbstract getInstance(){
        if (instance == null){
            instance = new TestReservationDAOFS();
        }
        return instance;
    }

    @Override
    void addTestReservation(TestReservation testReservation) throws DAOException, PropertyException, ResourceNotFoundException {

    }

    @Override
    public void insert(Test test){

    }

    @Override
    public void cancel(Test test){

    }

    @Override
    public void update(Test test){

    }

}
