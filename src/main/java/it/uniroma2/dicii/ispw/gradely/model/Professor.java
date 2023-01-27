package it.uniroma2.dicii.ispw.gradely.model;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.CourseAssignment;

import java.time.LocalDate;
import java.util.List;

public class Professor extends AbstractRole {
    private String id;
    private LocalDate registrationDate;


    private DipartimentoEnum dipartimento;
    private List<CourseAssignment> courseAssignments;
    private DegreeCourse coordinatedCourses;

    public Professor() {
    }

    public Professor(User user, String id, LocalDate registrationDate) {
        setUser(user);
        this.id = id;
        this.registrationDate = registrationDate;
    }

    @Override
    public Professor Professor() {
        return this;
    }
}
