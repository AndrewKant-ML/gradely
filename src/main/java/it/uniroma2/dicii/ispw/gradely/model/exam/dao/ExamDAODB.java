package it.uniroma2.dicii.ispw.gradely.model.exam.dao;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamDAODB extends AbstractExamDAO {

    private ExamDAODB(){
    }

    public static AbstractExamDAO getInstance(){
        if (instance == null) {
            instance = new ExamDAODB();
        }
        return instance;
    }

    @Override
    public Exam getExamByAppelloAndSubjectCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) {
        return null; //TODO implementare exceptions
    }

    @Override
    public void insert(Exam exam) {

    }

    @Override
    public void cancel(Exam exam) {

    }

    @Override
    public void update(Exam exam){
        System.out.println("Updated");
    }

    @Override
    public List<Exam> refresh(List<Exam> exams) {
        return null;
    }


}
