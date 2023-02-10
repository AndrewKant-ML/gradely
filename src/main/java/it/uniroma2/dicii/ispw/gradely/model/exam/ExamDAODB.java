package it.uniroma2.dicii.ispw.gradely.model.exam;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExamDAODB extends DAODBAbstract<Exam> implements AbstractExamDAO {
    protected static AbstractExamDAO instance;

    private ExamDAODB(){
    }

    public static synchronized AbstractExamDAO getInstance(){
        if (instance == null){
            instance = new ExamDAODB();
        }
        return instance;
    }

    @Override
    public Exam getExamByAppelloAndSubjectCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) throws DAOException {
        return null;
    }

    @Override
    public void insert(Exam exam){

    }

    @Override
    public void cancel(Exam exam){

    }

    @Override
    public void update(Exam exam){
        System.out.println("Updated");
    }

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, Exam exam) throws SQLException {

    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, Exam exam) throws SQLException, MissingAuthorizationException {

    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException {

    }


}
