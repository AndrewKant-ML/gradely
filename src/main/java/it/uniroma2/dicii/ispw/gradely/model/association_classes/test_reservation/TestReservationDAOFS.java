package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;

public class TestReservationDAOFS implements TestReservationDAOAbstract {

    private static TestReservationDAOFS instance;

    private TestReservationDAOFS() {
        super();
    }

    public static synchronized TestReservationDAOAbstract getInstance() {
        if (instance == null) {
            instance = new TestReservationDAOFS();
        }
        return instance;
    }

    @Override
    public void addTestReservation(TestReservation testReservation) {
        // To be implemented
    }

    public void insert(Test test){
        // To be implemented
    }

    public void delete(Test test){
        // To be implemented
    }

    public void update(Test test){
        // To be implemented
    }
}
