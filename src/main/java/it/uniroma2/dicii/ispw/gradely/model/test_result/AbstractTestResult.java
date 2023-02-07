package it.uniroma2.dicii.ispw.gradely.model.test_result;

public abstract class AbstractTestResult {

    private Boolean testResult;
    private Integer grade;
    private String message;

    public AbstractTestResult(Boolean testResult, Integer grade, String message) {
        this.testResult = testResult;
        this.grade = grade;
        this.message = message;
    }

    public Boolean getTestResult() {
        return testResult;
    }

    public String getMessage() {
        return message;
    }
}
