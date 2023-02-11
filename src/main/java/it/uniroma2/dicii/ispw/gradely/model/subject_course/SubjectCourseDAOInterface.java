package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.time.Year;


public interface SubjectCourseDAOInterface {

    SubjectCourse getSubjectCourseByNameCodeCfuAndAcademicYear(String name, SubjectCourseCodeEnum code, Integer cfu, Year academicYear) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException, UserNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue;

}
