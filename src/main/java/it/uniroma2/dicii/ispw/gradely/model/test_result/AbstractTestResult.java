package it.uniroma2.dicii.ispw.gradely.model.test_result;

public abstract class AbstractTestResult {

    private Boolean testResult;
    private Integer grade;
    private String message;

    public Boolean getTestResult() {
        return testResult;
    }

    public String getMessage() {
        return message;
    }
}
