package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.factory;

import it.uniroma2.dicii.ispw.gradely.enums.TestTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.AbstractTestResult;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries.AbstractTestBoundary;

public abstract class AbstractTestFactory {

    private static AbstractTestFactory INSTANCE = null;

    public static synchronized AbstractTestFactory getInstance(TestTypeEnum testTypeEnum) {
        // TODO change to session factory
        if (INSTANCE == null) {
            switch (testTypeEnum) {
                case ONLINE -> INSTANCE = new MoodleTestFactory();
                case MUR -> INSTANCE = new MURTestFactory();
            }
        }
        return INSTANCE;
    }

    protected AbstractTestFactory() {}

    public abstract AbstractTestBoundary createTestBoundary();

    public abstract AbstractTestResult createTestResult();
}
