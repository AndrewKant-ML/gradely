package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExamResultDAODB extends DAODBAbstract<ExamResult> implements ExamResultDAOInterface {
    protected static ExamResultDAOInterface instance;

    private ExamResultDAODB(){ 

    }

    public static synchronized ExamResultDAOInterface getInstance(){
        if (instance == null){
            instance = new ExamResultDAODB();
        }
        return instance;
    }


    @Override
    public ExamResult getExamResultByStudentAndExam(Student student, Exam exam) throws DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        try {
            return getQuery(
                    "EXAM_RESULT",
                    List.of("exam_sc_code", "exam_sc_name", "exam_sc_cfu", "exam_sc_aa", "exam_session", "exam_appello", "student"),
                    List.of(exam.getSubjectCourse().getCode().value, exam.getSubjectCourse().getName(), exam.getSubjectCourse().getCfu(), exam.getSubjectCourse().getAcademicYear(), exam.getSession().value, exam.getAppello().value, student),
                    List.of(exam, student)
            );
        } catch (UserNotFoundException e) {
            return null; // TBI manage UserNotFoundException
        }
    }

    @Override
    public void insert(ExamResult examResult) throws DAOException {
//TBI
    }

    @Override
    public void delete(ExamResult examResult) throws DAOException {
//TBI
    }

    @Override
    public void update(ExamResult examResult) throws DAOException {
//TBI
    }

    @Override
    protected ExamResult queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(ExamResult examResult, int valueNumber) throws DAOException {
        return null;
    }



}
