package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;

import java.time.Year;
import java.util.List;


public class SubjectCourseDAOFS extends SubjectCourseDAOAbstract {

    private SubjectCourseDAOFS(){
        super();
    }

    public static synchronized SubjectCourseDAOAbstract getInstance(){
        if (instance == null){
            instance = new SubjectCourseDAOFS();
        }
        return instance;
    }

    @Override
    public SubjectCourse getSubjectCourseByNameCodeCfuAndAcademicYear(String name, SubjectCourseCodeEnum code, Integer cfu, Year academicYear) throws DAOException {
        return null; 
    }

    @Override
    public void insert(SubjectCourse subjectCourse){

    }

    @Override
    public void cancel(SubjectCourse subjectCourse){

    }

    @Override
    public void update(SubjectCourse subjectCourse){

    }



}
