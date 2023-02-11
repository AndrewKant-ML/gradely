package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

public class ExamResultDAOFS implements ExamResultDAOInterface {

    private static ExamResultDAOFS instance;

    private ExamResultDAOFS(){ 

    }

    public static synchronized ExamResultDAOInterface getInstance(){
        if (instance == null){
            instance = new ExamResultDAOFS();
        }
        return instance;
    }

    @Override
    public ExamResult getExamResultByStudentAndExam(Student student, Exam exam) throws DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return null;
    }

    public void insert(ExamResult examResult) throws DAOException {

    }

    public void delete(ExamResult examResult) throws DAOException {

    }

    public void update(ExamResult examResult) throws DAOException {

    }

}
