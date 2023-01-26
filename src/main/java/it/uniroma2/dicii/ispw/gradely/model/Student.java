package it.uniroma2.dicii.ispw.gradely.model;

import it.uniroma2.dicii.ispw.gradely.enums.TitleEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.SubjectCourseEnrollment;

import java.time.LocalDate;
import java.util.List;

public class Student extends AbstractRole {
    private String ID;
    private LocalDate registrationDate;
    private List<TitleEnum> titoli;

    private List<DegreeCourseEnrollment> degreeCourseEnrollments;
    private List<SubjectCourseEnrollment> subjectCourseEnrollments;
    private List<ExamEnrollment> examEnrollments;

    public Student(User user, String ID, LocalDate registrationDate, List<TitleEnum> titoli) {
        setUser(user);
        this.ID = ID;
        this.registrationDate = registrationDate;
        this.titoli = titoli;
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
