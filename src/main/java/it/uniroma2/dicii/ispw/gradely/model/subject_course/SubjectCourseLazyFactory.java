package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SubjectCourseLazyFactory {
    private static SubjectCourseLazyFactory instance;
    private final List<SubjectCourse> subjectCourses;

    private SubjectCourseLazyFactory(){
        subjectCourses = new ArrayList<SubjectCourse>();
    }

    public static synchronized SubjectCourseLazyFactory getInstance(){
        if (instance == null){
            instance = new SubjectCourseLazyFactory();
        }
        return instance;
    }

<<<<<<< HEAD
    public SubjectCourse getSubjectCourseByCodeNameCfuAndAcademicYear(SubjectCourseCodeEnum code, String name, Integer cfu, Year academicYear) throws DAOException, PropertyException, ResourceNotFoundException {
=======
    public SubjectCourse getSubjectCourseByNameCodeCfuAy(String name, SubjectCourseCodeEnum code, Integer cfu, Year ay) throws DAOException {
>>>>>>> d4ec7c2 (  (lun 6 feb 2023, 17:12:07, CET))
        for(SubjectCourse s : subjectCourses){
            if(s.getCode().equals(code)&&s.getName().equals(name)&&s.getCfu().equals(cfu)&&s.getAcademicYear().equals(academicYear)){
                return s;
            }
        }
        SubjectCourse DAOCourse = DAOFactoryAbstract.getInstance().getSubjectCourseDAO().getSubjectCourseByName(name);
        subjectCourses.add(DAOCourse);
        return DAOCourse;
    }
}
