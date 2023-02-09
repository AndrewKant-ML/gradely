package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExamResultDAODB extends DAODBAbstract<ExamResult> implements AbstractExamResultDAO {
    protected static AbstractExamResultDAO instance;

    private ExamResultDAODB(){ 

    }

    public static synchronized AbstractExamResultDAO getInstance(){
        if (instance == null){
            instance = new ExamResultDAODB();
        }
        return instance;
    }



    @Override
    public void insert(ExamResult examResult) throws DAOException {

    }

    @Override
    public void cancel(ExamResult examResult) throws DAOException {

    }

    @Override
    public void update(ExamResult examResult) throws DAOException {

    }

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, ExamResult examResult) throws SQLException {

    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, ExamResult examResult) throws SQLException, MissingAuthorizationException {

    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException {

    }

}
