package it.uniroma2.dicii.ispw.gradely.model.exam.dao;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamDAOFS extends AbstractExamDAO {

    private ExamDAOFS(){ //TODO implementare costruttore vero

    }

    public static AbstractExamDAO getInstance(){
        if (instance == null) {
            instance = new ExamDAOFS();
        }
        return instance;
    }

    @Override
    public Exam getExamByAppelloCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) {
        return null; //TODO implementare exceptions
    }

    @Override
    public void insert(Exam exam) {

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