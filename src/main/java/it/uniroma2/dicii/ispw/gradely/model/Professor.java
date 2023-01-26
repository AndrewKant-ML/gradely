package it.uniroma2.dicii.ispw.gradely.model;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.CourseAssignment;

import java.time.LocalDate;
import java.util.List;

public class Professor extends AbstractRole {
    private String ID;
    private LocalDate registrationDate;
    private DipartimentoEnum dipartimento;
    private List<CourseAssignment> courseAssignments;
    private DegreeCourse coordinatedCourses;


    public Professor(User user, String ID, LocalDate registrationDate) {
        setUser(user);
        this.ID = ID;
        this.registrationDate = registrationDate;
    }

    @Override
    public User getUser() {
        return super.getUser();
    }

    @Override
    public void setUser(User user) {
        super.setUser(user);
    }
}
