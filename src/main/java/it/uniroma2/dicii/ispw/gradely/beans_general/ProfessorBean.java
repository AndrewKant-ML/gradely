package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.util.List;

public class ProfessorBean {
    private String matricola;
    private DipartimentoEnumBean dipartimento;
    private List<SubjectCourseAssignmentBean> subjectCourseAssignments;
    private DegreeCourseBean coordinatedCourse;

    public ProfessorBean(String matricola, DipartimentoEnumBean dipartimento, List<SubjectCourseAssignmentBean> subjectCourseAssignments, DegreeCourseBean coordinatedCourse) {
        this.matricola = matricola;
        this.dipartimento = dipartimento;
        this.subjectCourseAssignments = subjectCourseAssignments;
        this.coordinatedCourse = coordinatedCourse;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public DipartimentoEnumBean getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(DipartimentoEnumBean dipartimento) {
        this.dipartimento = dipartimento;
    }

    public List<SubjectCourseAssignmentBean> getSubjectCourseAssignments() {
        return subjectCourseAssignments;
    }

    public void setSubjectCourseAssignments(List<SubjectCourseAssignmentBean> subjectCourseAssignments) {
        this.subjectCourseAssignments = subjectCourseAssignments;
    }

    public DegreeCourseBean getCoordinatedCourse() {
        return coordinatedCourse;
    }

    public void setCoordinatedCourse(DegreeCourseBean coordinatedCourse) {
        this.coordinatedCourse = coordinatedCourse;
    }
}
