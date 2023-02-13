package it.uniroma2.dicii.ispw.gradely.model.exam;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExamDAOFS implements ExamDAOInterface {
    //tbi
    private static ExamDAOFS instance;

    private ExamDAOFS(){

    }

    public static synchronized ExamDAOInterface getInstance(){
        if (instance == null){
            instance = new ExamDAOFS();
        }
        return instance;
    }

    @Override
    public Exam getExamByAppelloAndSubjectCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) throws DAOException {
        return null;
    }

    @Override
    public List<Exam> getExamsBySubjectCourse(SubjectCourse subjectCourse, List<Exam> exclusions) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return new ArrayList<>();
    }

    @Override
    public Exam getExamById(UUID id) {
        return null;
    }

    @Override
    public void insert(Exam exam){
        // To be implemented
    }

    @Override
    public void delete(Exam exam){
        // To be implemented
    }

    @Override
    public void update(Exam exam){
        // To be implemented
    }


}
