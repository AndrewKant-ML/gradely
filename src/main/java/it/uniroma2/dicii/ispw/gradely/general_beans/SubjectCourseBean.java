package it.uniroma2.dicii.ispw.gradely.general_beans;

import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;

import java.time.Year;

public class SubjectCourseBean {
    private SubjectCourseCodeEnum code;
    private String name;
    private Year academicYear;

    public SubjectCourseBean(SubjectCourseCodeEnum code, String name, Year academicYear) {
        this.code = code;
        this.name = name;
        this.academicYear = academicYear;
    }

    public SubjectCourseCodeEnum getCode() {
        return code;
    }

    public void setCode(SubjectCourseCodeEnum code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Year academicYear) {
        this.academicYear = academicYear;
    }
}
