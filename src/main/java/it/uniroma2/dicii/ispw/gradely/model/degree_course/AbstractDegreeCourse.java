package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseTypeEnum;

public abstract class AbstractDegreeCourse {
    protected DegreeCourseCodeEnum code;

    protected DegreeCourseTypeEnum type;

    public AbstractDegreeCourse(DegreeCourseCodeEnum code, DegreeCourseTypeEnum type) {
        this.code = code;
        this.type = type;
    }

    public DegreeCourseCodeEnum getCode() {
        return code;
    }

    public void setCode(DegreeCourseCodeEnum code) {
        this.code = code;
    }

    public DegreeCourseTypeEnum getType() {
        return type;
    }

    public void setType(DegreeCourseTypeEnum type) {
        this.type = type;
    }
}
