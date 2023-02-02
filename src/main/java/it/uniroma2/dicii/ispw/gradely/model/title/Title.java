package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.AbstractDegreeCourse;

import java.time.LocalDate;

public class Title {

    private AbstractDegreeCourse degreeCourse;

    private LocalDate achievementDate;

    public Title(AbstractDegreeCourse degreeCourse, LocalDate achievementDate) {
        this.degreeCourse = degreeCourse;
        this.achievementDate = achievementDate;
    }

    public AbstractDegreeCourse getDegreeCourse() {
        return degreeCourse;
    }

    public void setDegreeCourse(AbstractDegreeCourse degreeCourse) {
        this.degreeCourse = degreeCourse;
    }

    public LocalDate getAchievementDate() {
        return achievementDate;
    }

    public void setAchievementDate(LocalDate achievementDate) {
        this.achievementDate = achievementDate;
    }
}
