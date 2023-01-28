package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.factory;

import it.uniroma2.dicii.ispw.gradely.model.AbstractTestResult;
import it.uniroma2.dicii.ispw.gradely.model.MoodleTestResult;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.boundary.AbstractTestBoundary;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.boundary.MoodleTestBoundary;

public class MoodleTestFactory extends AbstractTestFactory{
    @Override
    public AbstractTestBoundary createTestBoundary() {
        return new MoodleTestBoundary();
    }

    @Override
    public AbstractTestResult createTestResult() {
        return new MoodleTestResult();
    }
}
