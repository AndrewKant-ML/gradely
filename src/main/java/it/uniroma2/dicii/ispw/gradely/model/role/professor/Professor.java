package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.AbstractRole;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class Professor extends AbstractRole {
    private String matricola;
    private DipartimentoEnum dipartimento;
    private List<SubjectCourseAssignment> subjectCourseAssignments;
    private DegreeCourse coordinatedCourse;


    public Professor(User user, String matricola){
        super(user);
        this.matricola = matricola;
    }

    @Override
    public Professor castToProfessorRole() throws MissingAuthorizationException {
        return this;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public DipartimentoEnum getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(DipartimentoEnum dipartimento) {
        this.dipartimento = dipartimento;
    }

    public List<SubjectCourseAssignment> getCourseAssignments() {
        return subjectCourseAssignments;
    }

    public void setCourseAssignments(List<SubjectCourseAssignment> subjectCourseAssignments) {
        this.subjectCourseAssignments = subjectCourseAssignments;
    }

    public DegreeCourse getCoordinatedCourse() {
        return coordinatedCourse;
    }

    public void setCoordinatedCourse(DegreeCourse coordinatedCourse) {
        this.coordinatedCourse = coordinatedCourse;
    }
}
