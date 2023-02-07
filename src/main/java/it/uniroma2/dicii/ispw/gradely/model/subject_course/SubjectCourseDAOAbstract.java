package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.time.Year;


public abstract class SubjectCourseDAOAbstract implements DAOAbstract<SubjectCourse> {
    protected static SubjectCourseDAOAbstract instance;
    protected SubjectCourseDAOAbstract(){

    }

    public abstract SubjectCourse getSubjectCourseByNameCodeCfuAndAcademicYear(String name, SubjectCourseCodeEnum code, Integer cfu, Year academicYear) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException;

}
