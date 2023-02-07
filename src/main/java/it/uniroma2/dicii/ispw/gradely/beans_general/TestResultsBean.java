package it.uniroma2.dicii.ispw.gradely.beans_general;

public class TestResultsBean {
    private Boolean testResult;
    private Integer grade;
    private String message;

    public TestResultsBean(Boolean testResult, Integer grade, String message) {
        this.testResult = testResult;
        this.grade = grade;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
