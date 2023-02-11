package it.uniroma2.dicii.ispw.gradely.model.test_result;

import it.uniroma2.dicii.ispw.gradely.model.test.Test;

public class MURTestResult extends AbstractTestResult {

    public MURTestResult(Test test, Boolean testResult, Integer grade, String message) {
        super(test, testResult, grade, message);
    }
}
