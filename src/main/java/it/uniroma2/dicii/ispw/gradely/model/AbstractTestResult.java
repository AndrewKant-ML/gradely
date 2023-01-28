package it.uniroma2.dicii.ispw.gradely.model;

public abstract class AbstractTestResult {

    private Boolean testResult;
    private String message;

    public Boolean getTestResult() {
        return testResult;
    }

    public String getMessage() {
        return message;
    }
}
