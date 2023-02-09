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

    @Override
    public void insert(AbstractTestResult abstractTestResult){

    }

    @Override
    public void cancel(AbstractTestResult abstractTestResult){

    }

    @Override
    public void update(AbstractTestResult abstractTestResult){

    }

}
