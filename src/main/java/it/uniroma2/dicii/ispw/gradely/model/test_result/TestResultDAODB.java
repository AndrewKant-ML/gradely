package it.uniroma2.dicii.ispw.gradely.model.test_result;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TestResultDAODB extends DAODBAbstract<AbstractTestResult> implements TestResultDAOInterface {
    protected static TestResultDAOInterface instance;

    private TestResultDAODB(){
        super();
    }
    public static synchronized TestResultDAOInterface getInstance(){
        if (instance == null){
            instance = new TestResultDAODB();
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

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, AbstractTestResult abstractTestResult) throws SQLException {

    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, AbstractTestResult abstractTestResult) throws SQLException, MissingAuthorizationException {

    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException {

    }

}
