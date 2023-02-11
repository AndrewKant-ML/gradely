package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

public interface TestReservationDAOAbstract  {

    abstract void addTestReservation(TestReservation testReservation) throws DAOException, PropertyException, ResourceNotFoundException;
}
