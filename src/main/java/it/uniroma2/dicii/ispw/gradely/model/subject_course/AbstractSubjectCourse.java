package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;

public abstract class AbstractSubjectCourse {
    private SubjectCourseCodeEnum code;

    protected AbstractSubjectCourse(SubjectCourseCodeEnum code){
        this.code = code;
    }

    public SubjectCourseCodeEnum getCode(){
        return code;
    }

    public void setCode(SubjectCourseCodeEnum code){
        this.code = code;
    }
}
