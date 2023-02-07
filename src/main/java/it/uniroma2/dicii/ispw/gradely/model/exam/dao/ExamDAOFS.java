package it.uniroma2.dicii.ispw.gradely.model.exam.dao;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;

public class ExamDAOFS extends AbstractExamDAO {

    private ExamDAOFS(){

    }

    public static synchronized AbstractExamDAO getInstance(){
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
    public void insert(Exam exam){

    }

    @Override
    public void cancel(Exam exam){

    }

    @Override
    public void update(Exam exam){
        System.out.println("Updated");
    }


}
