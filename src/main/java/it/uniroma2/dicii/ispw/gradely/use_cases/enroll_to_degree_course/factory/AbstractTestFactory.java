package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.factory;

import it.uniroma2.dicii.ispw.gradely.enums.TestTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.test_result.AbstractTestResult;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries.AbstractTestBoundary;

public abstract class AbstractTestFactory {

    private static AbstractTestFactory instance = null;

    public static synchronized AbstractTestFactory getInstance(TestTypeEnum testTypeEnum){
        // TODO change to session factory
        if (instance == null){
            switch (testTypeEnum){
                case ONLINE -> instance = new MoodleTestFactory();
                case MUR -> instance = new MURTestFactory();
            }
        }
        return instance;
    }

    protected AbstractTestFactory(){}

    public abstract AbstractTestBoundary createTestBoundary();

    public abstract AbstractTestResult createTestResult(Boolean testResult, Integer grade, String message);
    //TODO rivedere forse ci vuole un bean e non un test result model object
}
