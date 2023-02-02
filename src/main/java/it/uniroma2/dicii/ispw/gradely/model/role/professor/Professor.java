package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.AbstractRole;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.time.LocalDate;
import java.util.List;

public class Professor extends AbstractRole {
    private String matricola;
    private LocalDate registrationDate;
    private DipartimentoEnum dipartimento;
    private List<CourseAssignment> courseAssignments;
    private DegreeCourse coordinatedCourses;


    public Professor(User user, String matricola, LocalDate registrationDate){
        super(user);
        this.matricola = matricola;
        this.registrationDate = registrationDate;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public DipartimentoEnum getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(DipartimentoEnum dipartimento) {
        this.dipartimento = dipartimento;
    }

    public List<CourseAssignment> getCourseAssignments() {
        return courseAssignments;
    }

    public void setCourseAssignments(List<CourseAssignment> courseAssignments) {
        this.courseAssignments = courseAssignments;
    }

    public DegreeCourse getCoordinatedCourses() {
        return coordinatedCourses;
    }

    public void setCoordinatedCourses(DegreeCourse coordinatedCourses) {
        this.coordinatedCourses = coordinatedCourses;
    }
}
