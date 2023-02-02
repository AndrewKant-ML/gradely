package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.model.test.Test;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.EnrollToDegreeCourseController;

import java.time.LocalDate;

public class TestResultTimer extends AbstractTimer<Test, EnrollToDegreeCourseController>{

    protected TestResultTimer(LocalDate expiration, Test test){
        super(expiration, test);
    }

    @Override
    public TestResultTimer castToTestResultTimer(){
        return this;
    }

}
