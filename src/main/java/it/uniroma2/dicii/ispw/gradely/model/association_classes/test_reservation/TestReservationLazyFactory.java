package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;

import java.util.ArrayList;
import java.util.List;

public class TestReservationLazyFactory {

    private static TestReservationLazyFactory instance;
    private final List<TestReservation> testReservations;

    private TestReservationLazyFactory() {
        this.testReservations = new ArrayList<>();
    }

    public static TestReservationLazyFactory getInstance() {
        if (instance == null)
            instance = new TestReservationLazyFactory();
        return instance;
    }

    /**
     * Saves a Student's test reservation
     *
     * @param student the Student whose test reservation has to be saves
     * @param test    the test to which the student wants to reserve to
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading db connection properties OR thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    public void reserveTest(Student student, Test test) throws PropertyException, ResourceNotFoundException, DAOException {
        TestReservation newTestReservation = new TestReservation(student, test);
        DAOFactoryAbstract.getInstance().getTestReservationDAO().addTestReservation(newTestReservation);
        testReservations.add(newTestReservation);
    }


}
