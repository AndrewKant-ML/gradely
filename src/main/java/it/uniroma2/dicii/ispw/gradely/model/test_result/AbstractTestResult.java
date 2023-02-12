package it.uniroma2.dicii.ispw.gradely.model.test_result;

import it.uniroma2.dicii.ispw.gradely.model.test.Test;

public abstract class AbstractTestResult {

    private Test test;
    private Boolean testResult;
    private Integer grade;
    private String message;

    protected AbstractTestResult(Test test, Boolean testResult, Integer grade, String message) {
        this.test = test;
        this.testResult = testResult;
        this.grade = grade;
        this.message = message;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Boolean getTestResult() {
        return testResult;
    }

    public void setTestResult(Boolean testResult) {
        this.testResult = testResult;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
