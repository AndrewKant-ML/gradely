package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.factory;

import it.uniroma2.dicii.ispw.gradely.model.test.Test;
import it.uniroma2.dicii.ispw.gradely.model.test_result.AbstractTestResult;
import it.uniroma2.dicii.ispw.gradely.model.test_result.MURTestResult;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries.AbstractTestBoundary;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries.MURTestBoundary;

public class MURTestFactory extends AbstractTestFactory {
    @Override
    public AbstractTestBoundary createTestBoundary(){
        return new MURTestBoundary();
    }

    @Override
    public AbstractTestResult createTestResult(Test test, Boolean testResult, Integer grade, String message){
        return new MURTestResult(test, testResult, grade, message);
    }
}
