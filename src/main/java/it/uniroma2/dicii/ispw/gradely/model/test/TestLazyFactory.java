package it.uniroma2.dicii.ispw.gradely.model.test;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Test saveTestData(DegreeCourse degreeCourse, UUID id, LocalDate testDate, URL testReservationLink, LocalDate resultsDate, URL infoLink, String place) throws PropertyException, ResourceNotFoundException, DAOException {
        // Checks if the test is already loaded
        for (Test test : tests)
            if (test.getId().equals(id))
                return test;
        // Check if the test is already saved into the persistence layer
        Test test;
        try {
            test = DAOFactoryAbstract.getInstance().getTestDAO().getTestById(id.toString());
        } catch (ObjectNotFoundException | WrongDegreeCourseCodeException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        if (test != null)
            return test;
        Test newTest = new Test(
                degreeCourse, id, testDate, testReservationLink, resultsDate, infoLink, place
        );
        DAOFactoryAbstract.getInstance().getTestDAO().saveTestInfo(newTest);
        tests.add(newTest);
        return newTest;
    }

    public Test getTestById(UUID id) throws PropertyException, ResourceNotFoundException, ObjectNotFoundException, DAOException, WrongDegreeCourseCodeException {
        for (Test test : tests)
            if (test.getId().equals(id))
                return test;
        return DAOFactoryAbstract.getInstance().getTestDAO().getTestById(id.toString());
    }
}
