package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries;

import it.uniroma2.dicii.ispw.gradely.exceptions.TestRetrivialException;
import it.uniroma2.dicii.ispw.gradely.general_beans.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.TestResultsBean;
import it.uniroma2.dicii.ispw.gradely.model.TestInfo;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;

public class MoodleTestBoundary extends AbstractTestBoundary {
    @Override
    public TestInfoBean getTestInfo() throws TestRetrivialException {
        return null;
    }

    @Override
    public TestReservationBean reserveTest(String testId) {
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
