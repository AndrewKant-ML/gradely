package it.uniroma2.dicii.ispw.gradely.model.test;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestLazyFactory {

    private static TestLazyFactory instance;

    private final List<Test> tests;

    private TestLazyFactory() {
        tests = new ArrayList<>();
    }

    public static TestLazyFactory getInstance() {
        if (instance == null)
            instance = new TestLazyFactory();
        return instance;
    }

    public void saveTestData(DegreeCourse degreeCourse, String id, LocalDate testDate, URL testReservationLink, LocalDate resultsDate, URL infoLink, String place) throws PropertyException, ResourceNotFoundException, DAOException {
        Test newTest = new Test(
                degreeCourse, id, testDate, testReservationLink, resultsDate, infoLink, place
        );
        DAOFactoryAbstract.getInstance().getTestDAO().saveTestInfo(newTest);
        tests.add(newTest);
    }

    public Test getTestById(String id) throws PropertyException, ResourceNotFoundException, ObjectNotFoundException, DAOException {
        for (Test test : tests)
            if (test.getID().equals(id))
                return test;
        return DAOFactoryAbstract.getInstance().getTestDAO().getTestById(id);
    }
}
