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

    }

    public void cancel(AbstractTestResult abstractTestResult){

    }

    public void update(AbstractTestResult abstractTestResult){

    }

}
