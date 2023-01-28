package it.uniroma2.dicii.ispw.gradely.general_beans;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseTypeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.TestTypeEnum;

public class DegreeCourseBean {

    private String name;
    private String facolta;
    private DegreeCourseTypeEnum type;

    private TestTypeEnum testTypeEnum;

    public DegreeCourseBean(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public TestTypeEnum getTestTypeEnum() {
        return testTypeEnum;
    }
}
