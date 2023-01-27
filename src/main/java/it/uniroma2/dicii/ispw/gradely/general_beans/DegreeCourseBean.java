package it.uniroma2.dicii.ispw.gradely.general_beans;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseTypeEnum;

public class DegreeCourseBean {

    private String name;
    private String facolta;
    private DegreeCourseTypeEnum type;

    public DegreeCourseBean(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
