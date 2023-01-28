package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.boundary;

import it.uniroma2.dicii.ispw.gradely.general_beans.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.TestResultsBean;

public abstract class AbstractTestBoundary {

    public abstract TestInfoBean getTestInfo();

    public abstract TestReservationBean reserveTest();

    public abstract Boolean checkResultsPresence(TestInfoBean testInfo);

    public abstract TestResultsBean getTestResults(TestInfoBean testInfo);
}
