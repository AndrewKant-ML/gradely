package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;

public abstract class AbstractDegreeCourse {
    private DegreeCourseCodeEnum code;

    public DegreeCourseCodeEnum getCode() {
        return code;
    }

    public void setCode(DegreeCourseCodeEnum code) {
        this.code = code;
    }
}
