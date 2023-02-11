package it.uniroma2.dicii.ispw.gradely.model.exam;

import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ExamDAODB extends DAODBAbstract<Exam> implements ExamDAOInterface {
    protected static ExamDAOInterface instance;

    private ExamDAODB(){
    }

    public static synchronized ExamDAOInterface getInstance(){
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
    public Exam getExamById(UUID id) {
        return null;
    }

    @Override
    public void insert(Exam exam){

    }

    @Override
    public void delete(Exam exam){

    }

    @Override
    public void update(Exam exam){
        System.out.println("Updated");
    }

    @Override
    protected Exam queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(Exam exam, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }


}
