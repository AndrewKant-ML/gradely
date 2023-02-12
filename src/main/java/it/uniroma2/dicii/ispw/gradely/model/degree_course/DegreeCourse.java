package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import it.uniroma2.dicii.ispw.gradely.enums.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;

public class DegreeCourse extends AbstractDegreeCourse {
    private String name;
    private FacoltaEnum facolta;
    private DipartimentoEnum dipartimento;
    private List<AbstractDegreeCourse> prerequisites;
    private Professor coordinatore;
    private List<DegreeCourseEnrollment> enrollments;
    private List<SubjectCourse> subjectCourses;
    private TestTypeEnum testType;

    // TBI make package protected
    public DegreeCourse(DegreeCourseCodeEnum code, String name, FacoltaEnum facolta, DipartimentoEnum dipartimento, DegreeCourseTypeEnum type, TestTypeEnum testType) {
        super(code, type);
        this.name = name;
        this.facolta = facolta;
        this.dipartimento = dipartimento;
        this.type = type;
        this.testType = testType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public FacoltaEnum getFacolta(){
        return facolta;
    }

    public void setFacolta(FacoltaEnum facolta){
        this.facolta = facolta;
    }

    public DipartimentoEnum getDipartimento(){
        return dipartimento;
    }

    public void setDipartimento(DipartimentoEnum dipartimento){
        this.dipartimento = dipartimento;
    }

    public List<AbstractDegreeCourse> getPrerequisites(){
        return prerequisites;
    }

    public void setPrerequisites(List<AbstractDegreeCourse> prerequisites){
        this.prerequisites = prerequisites;
    }

    public Professor getCoordinatore(){
        return coordinatore;
    }

    public void setCoordinatore(Professor coordinatore){
        this.coordinatore = coordinatore;
    }

    public List<DegreeCourseEnrollment> getEnrollments(){
        return enrollments;
    }

    public void setEnrollments(List<DegreeCourseEnrollment> enrollments){
        this.enrollments = enrollments;
    }

    public List<SubjectCourse> getSubjectCourses(){
        return subjectCourses;
    }

    public void setSubjectCourses(List<SubjectCourse> subjectCourses){
        this.subjectCourses = subjectCourses;
    }

    public TestTypeEnum getTestType(){
        return testType;
    }

    public void setTestType(TestTypeEnum testType){
        this.testType = testType;
    }
}
