package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.role.AbstractRole;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.time.LocalDate;
import java.util.List;

public class Professor extends AbstractRole {
    private String id;
    private LocalDate registrationDate;


    private DipartimentoEnum dipartimento;
    private List<CourseAssignment> courseAssignments;
    private DegreeCourse coordinatedCourses;


    public Professor(User user, String id, LocalDate registrationDate) {
        super(user);
        this.id = id;
        this.registrationDate = registrationDate;
    }

    @Override
    public Professor professor() throws MissingAuthorizationException {
        return this;
    }
}
