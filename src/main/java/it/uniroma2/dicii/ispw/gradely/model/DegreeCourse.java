package it.uniroma2.dicii.ispw.gradely.model;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseTypeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;

import java.util.List;

public class DegreeCourse extends AbstractDegreeCourse{
    private String name;
    private String facolta; //TODO: enum
    private DipartimentoEnum dipartimento;
    private DegreeCourseTypeEnum type;
    private List<AbstractDegreeCourse> prerequisites;

    private Professor coordinatore;
    private List <DegreeCourseEnrollment> enrollments;
    private List <SubjectCourse> subjectCourses;

    public DegreeCourse(){

    }
    public DegreeCourse(String name, String facolta, DipartimentoEnum dipartimento, DegreeCourseTypeEnum type, List<AbstractDegreeCourse> prerequisites) {
        this.name = name;
        this.facolta = facolta;
        this.dipartimento = dipartimento;
        this.type = type;
        this.prerequisites = prerequisites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacolta() {
        return facolta;
    }

    public void setFacolta(String facolta) {
        this.facolta = facolta;
    }

    public DipartimentoEnum getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(DipartimentoEnum dipartimento) {
        this.dipartimento = dipartimento;
    }

    public DegreeCourseTypeEnum getType() {
        return type;
    }

    public void setType(DegreeCourseTypeEnum type) {
        this.type = type;
    }

    public List<AbstractDegreeCourse> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<AbstractDegreeCourse> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Professor getCoordinatore() {
        return coordinatore;
    }

    public void setCoordinatore(Professor coordinatore) {
        this.coordinatore = coordinatore;
    }

    public List<DegreeCourseEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<DegreeCourseEnrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<SubjectCourse> getSubjectCourses() {
        return subjectCourses;
    }

    public void setSubjectCourses(List<SubjectCourse> subjectCourses) {
        this.subjectCourses = subjectCourses;
    }
}
