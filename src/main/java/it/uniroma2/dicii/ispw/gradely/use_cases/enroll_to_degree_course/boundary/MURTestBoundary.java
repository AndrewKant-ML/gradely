package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.boundary;

import it.uniroma2.dicii.ispw.gradely.general_beans.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.TestResultsBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;

public class MURTestBoundary extends AbstractTestBoundary {
    @Override
    public TestInfoBean getTestInfo() {
        return null;
    }

    @Override
    public TestReservationBean reserveTest() {
        return null;
    }

    @Override
    public Boolean checkResultsPresence(TestInfoBean testInfo) {
        return null;
    }

    @Override
    public TestResultsBean getTestResults(TestInfoBean testInfo) {
        return null;
    }
}
