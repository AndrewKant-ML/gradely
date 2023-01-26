package it.uniroma2.dicii.ispw.gradely.beans;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseTypeEnum;

public class DegreeCourseBean {

    private String name;
    private String facolta;
    private DegreeCourseTypeEnum type;

    public DegreeCourseBean(String name, String facolta, DegreeCourseTypeEnum type) {
        this.name = name;
        this.facolta = facolta;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
