package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries;

import it.uniroma2.dicii.ispw.gradely.exceptions.TestRetrivialException;
import it.uniroma2.dicii.ispw.gradely.general_beans.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.TestResultsBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class MURTestBoundary extends AbstractTestBoundary {
    @Override
    public TestInfoBean getTestInfo() throws TestRetrivialException {
        try {
            return new TestInfoBean(
                    "MUR-test",
                    LocalDate.of(2023, 7, 4),
                    new URL("https://www.mur.gov.it/it"),
                    new URL("https://www.mur.gov.it/it"),
                    "Facoltà di ingegneria, Tor Vergata"
            );
        } catch (MalformedURLException e) {
            throw new TestRetrivialException("Error occurred while retrieving test info on MUR platform", e);
        }
    }

    @Override
    public TestReservationBean reserveTest(String testId) {
        return new TestReservationBean(testId);
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
