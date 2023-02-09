package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student){
        return null;
    }

    @Override
    public void insert(SubjectCourseEnrollment subjectCourseEnrollment){

    }

    @Override
    public void cancel(SubjectCourseEnrollment subjectCourseEnrollment){

    }

    @Override
    public void update(SubjectCourseEnrollment subjectCourseEnrollment){

    }

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, SubjectCourseEnrollment subjectCourseEnrollment) throws SQLException {

    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, SubjectCourseEnrollment subjectCourseEnrollment) throws SQLException, MissingAuthorizationException {

    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException {

    }


}