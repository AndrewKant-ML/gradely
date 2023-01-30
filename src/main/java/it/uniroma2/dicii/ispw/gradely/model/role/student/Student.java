package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.enums.TitleEnum;
import it.uniroma2.dicii.ispw.gradely.model.role.AbstractRole;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.time.LocalDate;
import java.util.List;

public class Student extends AbstractRole {
    private String id;
    private LocalDate registrationDate;
    private List<TitleEnum> titles;

    private List<DegreeCourseEnrollment> degreeCourseEnrollments;
    private List<SubjectCourseEnrollment> subjectCourseEnrollments;
    private List<ExamEnrollment> examEnrollments;

    protected Student(User user, String id, LocalDate registrationDate, List<TitleEnum> titoli) {
        super(user);
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
