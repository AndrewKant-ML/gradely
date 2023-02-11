package it.uniroma2.dicii.ispw.gradely.model.test_result;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    protected AbstractTestResult queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(AbstractTestResult abstractTestResult, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }

}
