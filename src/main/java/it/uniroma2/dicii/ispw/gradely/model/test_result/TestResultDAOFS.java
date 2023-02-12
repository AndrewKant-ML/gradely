package it.uniroma2.dicii.ispw.gradely.model.test_result;

public class TestResultDAOFS implements TestResultDAOInterface {
    protected static TestResultDAOInterface instance;

    private TestResultDAOFS(){
        super();
    }

    public static synchronized TestResultDAOInterface getInstance(){
        if (instance == null){
            instance = new TestResultDAOFS();
        }
        return instance;
    }

    public void insert(AbstractTestResult abstractTestResult){
        // To be implemented
    }

    public void delete(AbstractTestResult abstractTestResult){
        // To be implemented
    }

    public void update(AbstractTestResult abstractTestResult){
        // To be implemented
    }
}
