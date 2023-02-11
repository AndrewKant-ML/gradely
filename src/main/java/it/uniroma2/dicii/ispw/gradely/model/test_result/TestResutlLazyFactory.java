package it.uniroma2.dicii.ispw.gradely.model.test_result;

import java.util.ArrayList;
import java.util.List;

public class TestResutlLazyFactory {
    private static TestResutlLazyFactory instance;
    private final List<AbstractTestResult> testResults;

    private TestResutlLazyFactory() {
        this.testResults = new ArrayList<>();
    }

    public static TestResutlLazyFactory getInstance(){
        if (instance == null)
            instance = new TestResutlLazyFactory();
        return instance;
    }

    public AbstractTestResult getTestResultById(Integer id){
        return null;
    }

}
