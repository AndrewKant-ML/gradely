package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries;

import it.uniroma2.dicii.ispw.gradely.exceptions.TestRetrivialException;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestResultsBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;

public abstract class AbstractTestBoundary {

    public abstract TestInfoBean getTestInfo() throws TestRetrivialException;

    public abstract TestReservationBean reserveTest(String testId);

    public abstract Boolean checkResultsPresence(TestInfoBean testInfo);

    public abstract TestResultsBean getTestResults(TestInfoBean testInfo);
}
