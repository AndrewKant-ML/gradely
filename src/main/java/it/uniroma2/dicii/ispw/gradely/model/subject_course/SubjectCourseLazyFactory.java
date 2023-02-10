package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SubjectCourseLazyFactory {
    private static SubjectCourseLazyFactory instance;
    private final List<SubjectCourse> subjectCourses;

    private SubjectCourseLazyFactory(){
        subjectCourses = new ArrayList<>();
    }

    public static synchronized SubjectCourseLazyFactory getInstance(){
        if (instance == null){
            instance = new SubjectCourseLazyFactory();
        }
        return instance;
    }

    public SubjectCourse getSubjectCourseByCodeNameCfuAndAcademicYear(SubjectCourseCodeEnum code, String name, Integer cfu, Year academicYear) throws DAOException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UserNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        for (SubjectCourse s : subjectCourses) {
            if (s.getCode().equals(code) && s.getName().equals(name) && s.getCfu().equals(cfu) && s.getAcademicYear().equals(academicYear)) {
                return s;
            }
        }
        SubjectCourse daoCourse = DAOFactoryAbstract.getInstance().getSubjectCourseDAO().getSubjectCourseByNameCodeCfuAndAcademicYear(name, code, cfu, academicYear);
        subjectCourses.add(daoCourse);
        return daoCourse;
    }
}
