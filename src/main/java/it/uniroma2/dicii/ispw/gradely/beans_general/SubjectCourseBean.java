package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;

import java.time.Year;

public class SubjectCourseBean {
    private SubjectCourseCodeEnum code;
    private String name;
    private Integer cfu;
    private Year academicYear;

    public SubjectCourseBean(SubjectCourseCodeEnum code, String name, Integer cfu, Year academicYear){
        this.code = code;
        this.name = name;
        this.cfu = cfu;
        this.academicYear = academicYear;
    }

    public SubjectCourseCodeEnum getCode(){
        return code;
    }

    public void setCode(SubjectCourseCodeEnum code){
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getCfu() {
        return cfu;
    }

    public void setCfu(Integer cfu) {
        this.cfu = cfu;
    }

    public Year getAcademicYear(){
        return academicYear;
    }

    public void setAcademicYear(Year academicYear){
        this.academicYear = academicYear;
    }
}
