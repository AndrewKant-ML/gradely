package it.uniroma2.dicii.ispw.gradely.model;

import it.uniroma2.dicii.ispw.gradely.enums.TitleEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.SubjectCourseEnrollment;

import java.time.LocalDate;
import java.util.List;

public class Student extends AbstractRole {
    private String id;
    private LocalDate registrationDate;
    private List<TitleEnum> titles;

    private List<DegreeCourseEnrollment> degreeCourseEnrollments;
    private List<SubjectCourseEnrollment> subjectCourseEnrollments;
    private List<ExamEnrollment> examEnrollments;

    public Student(User user, String id, LocalDate registrationDate, List<TitleEnum> titoli) {
        setUser(user);
        this.id = id;
        this.registrationDate = registrationDate;
        this.titles = titoli;
    }

    @Override
    public Student student() {
        return this;
    }

    public String getId() {
        return id;
    }
}
