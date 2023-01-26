package it.uniroma2.dicii.ispw.gradely.model;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseTypeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;

import java.util.List;

public class DegreeCourse extends AbstractDegreeCourse{
    private String nome;
    private String facolta; //TODO: enum
    private DipartimentoEnum dipartimento;
    private DegreeCourseTypeEnum type;
    private List<AbstractDegreeCourse> prerequisites;

    private Professor coordinatore;
    private List <DegreeCourseEnrollment> enrollments;
    private List <SubjectCourse> subjectCourses;

    public DegreeCourse(String nome, String facolta, DipartimentoEnum dipartimento, DegreeCourseTypeEnum type, List<AbstractDegreeCourse> prerequisites) {
        this.nome = nome;
        this.facolta = facolta;
        this.dipartimento = dipartimento;
        this.type = type;
        this.prerequisites = prerequisites;
    }
}
