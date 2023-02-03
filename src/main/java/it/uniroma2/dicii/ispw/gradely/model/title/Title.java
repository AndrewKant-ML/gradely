package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.AbstractDegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.time.LocalDate;

public class Title {

    private AbstractDegreeCourse degreeCourse;
    private Student student;
    private LocalDate achievementDate;

    public Title(AbstractDegreeCourse degreeCourse, Student student, LocalDate achievementDate) {
        this.degreeCourse = degreeCourse;
        this.student = student;
        this.achievementDate = achievementDate;
    }

    public AbstractDegreeCourse getDegreeCourse() {
        return degreeCourse;
    }

    public void setDegreeCourse(AbstractDegreeCourse degreeCourse) {
        this.degreeCourse = degreeCourse;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getAchievementDate() {
        return achievementDate;
    }

    public void setAchievementDate(LocalDate achievementDate) {
        this.achievementDate = achievementDate;
    }
}
