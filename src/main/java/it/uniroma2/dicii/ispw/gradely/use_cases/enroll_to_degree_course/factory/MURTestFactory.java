package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.factory;

import it.uniroma2.dicii.ispw.gradely.model.AbstractTestResult;
import it.uniroma2.dicii.ispw.gradely.model.MURTestResult;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries.AbstractTestBoundary;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries.MURTestBoundary;

public class MURTestFactory extends AbstractTestFactory {
    @Override
    public AbstractTestBoundary createTestBoundary() {
        return new MURTestBoundary();
    }

    @Override
    public AbstractTestResult createTestResult() {
        return new MURTestResult();
    }
}
