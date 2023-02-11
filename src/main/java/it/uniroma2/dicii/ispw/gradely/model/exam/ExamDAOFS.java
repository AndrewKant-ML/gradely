package it.uniroma2.dicii.ispw.gradely.model.exam;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

public class ExamDAOFS implements AbstractExamDAO {

    private static ExamDAOFS instance;

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

    public void insert(Exam exam){

    }

    public void cancel(Exam exam){

    }

    public void update(Exam exam){
        System.out.println("Updated");
    }


}
