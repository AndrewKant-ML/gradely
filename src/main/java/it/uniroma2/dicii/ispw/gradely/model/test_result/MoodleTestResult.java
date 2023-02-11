package it.uniroma2.dicii.ispw.gradely.model.test_result;

import it.uniroma2.dicii.ispw.gradely.model.test.Test;

import java.util.UUID;

public class MoodleTestResult extends AbstractTestResult {

    public MoodleTestResult(Test test, Boolean testResult, Integer grade, String message) {
        super(test, testResult, grade, message);
    }
}
