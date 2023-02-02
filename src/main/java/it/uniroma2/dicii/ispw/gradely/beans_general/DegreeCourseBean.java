package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseTypeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.TestTypeEnum;

public class DegreeCourseBean {

    private String name;
    private String facolta;
    private DegreeCourseTypeEnum type;

    private TestTypeEnum testType;

    public DegreeCourseBean(String name, String facolta, DegreeCourseTypeEnum type, TestTypeEnum testType){
        this.name = name;
        this.facolta = facolta;
        this.type = type;
        this.testType = testType;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public TestTypeEnum getTestType(){
        return testType;
    }

    public String getName(){
        return name;
    }

    public String getFacolta(){
        return facolta;
    }

    public DegreeCourseTypeEnum getType(){
        return type;
    }
}
