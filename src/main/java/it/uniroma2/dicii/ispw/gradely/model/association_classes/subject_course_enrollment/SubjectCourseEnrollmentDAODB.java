package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectCourseEnrollmentDAODB extends DAODBAbstract<SubjectCourseEnrollment> implements AbstractSubjectCourseEnrollmentDAO {
    protected static AbstractSubjectCourseEnrollmentDAO instance;

    private SubjectCourseEnrollmentDAODB(){

    }

    public static synchronized AbstractSubjectCourseEnrollmentDAO getInstance(){
        if (instance == null){
            instance = new SubjectCourseEnrollmentDAODB();
        }
        return instance;
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course){
        return new ArrayList<>();
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student){
        return new ArrayList<>(); //TBI
    }

    @Override
    public void insert(SubjectCourseEnrollment subjectCourseEnrollment){
//TBI
    }

    @Override
    public void delete(SubjectCourseEnrollment subjectCourseEnrollment){
//TBI
    }

    @Override
    public void update(SubjectCourseEnrollment subjectCourseEnrollment){
//TBI
    }

    @Override
    protected SubjectCourseEnrollment queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(SubjectCourseEnrollment subjectCourseEnrollment, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }

}