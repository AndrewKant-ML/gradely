package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;

public abstract class TestReservationDAOAbstract implements DAOInterface<Test> {
    protected static TestReservationDAOAbstract instance;

    protected TestReservationDAOAbstract(){
    }

    abstract void addTestReservation(TestReservation testReservation) throws DAOException, PropertyException, ResourceNotFoundException;
}
