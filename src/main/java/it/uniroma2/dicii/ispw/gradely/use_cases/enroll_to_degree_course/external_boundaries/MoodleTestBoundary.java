package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.external_boundaries;

import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestResultsBean;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.TestRetrivialException;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.TestReservationBean;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class MoodleTestBoundary extends AbstractTestBoundary {
    @Override
    public TestInfoBean getTestInfo() throws TestRetrivialException {
        try {
            return new TestInfoBean(
                    "MOODLE-test",
                    LocalDate.of(2023, 7, 4),
                    new URL("https://moodle.org/?lang=it"),
                    LocalDate.of(2023, 8, 4),
                    new URL("https://moodle.org/?lang=itb"),
                    "Facoltà di ingegneria, Tor Vergata"
            );
        } catch (MalformedURLException e){
            throw new TestRetrivialException(ExceptionMessagesEnum.TEST_RETRIEVAL_MOODLE.message, e);
        }
    }

    @Override
    public TestReservationBean reserveTest(String testId){
        return new TestReservationBean(testId);
    }

    @Override
    public Boolean checkResultsPresence(TestInfoBean testInfo){
        return null;
    }

    @Override
    public TestResultsBean getTestResults(TestInfoBean testInfo){
        return null;
    }
}
