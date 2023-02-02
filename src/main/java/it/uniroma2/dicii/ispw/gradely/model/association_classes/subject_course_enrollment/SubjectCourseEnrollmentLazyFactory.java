package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseEnrollmentLazyFactory {
    private static SubjectCourseEnrollmentLazyFactory instance;
    private List<SubjectCourseEnrollment> subjectCourseEnrollments;

    private SubjectCourseEnrollmentLazyFactory(){
        subjectCourseEnrollments = new ArrayList<SubjectCourseEnrollment>();
    }

    public static synchronized SubjectCourseEnrollmentLazyFactory getInstance(){
        if (instance == null){
            instance = new SubjectCourseEnrollmentLazyFactory();
        }
        return instance;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course) throws DAOException {
        List<SubjectCourseEnrollment> list = new ArrayList<>();
        for(SubjectCourseEnrollment e : subjectCourseEnrollments){
            if(e.getSubjectCourse().equals(course)){
                list.add(e);
            }
        }
        List<SubjectCourseEnrollment> daoList = DAOFactoryAbstract.getInstance().getSubjectCourseEnrollmentDAO().getSubjectCourseEnrollmentsBySubjectCourse(course);
        for(SubjectCourseEnrollment e : daoList){
            if(!list.contains(e)){
                list.add(e);
            }
        }
        return list;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student) throws DAOException {
        List<SubjectCourseEnrollment> list = new ArrayList<>();
        for(SubjectCourseEnrollment e : subjectCourseEnrollments){
            if(e.getStudent().equals(student)){
                list.add(e);
            }
        }
        List<SubjectCourseEnrollment> daoList = DAOFactoryAbstract.getInstance().getSubjectCourseEnrollmentDAO().getSubjectCourseEnrollmentsByStudent(student);
        for(SubjectCourseEnrollment e : daoList){
            if(!list.contains(e)){
                list.add(e);
            }
        }
        return list;
    }
}