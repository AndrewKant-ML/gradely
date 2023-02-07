package it.uniroma2.dicii.ispw.gradely.beans_general;

import java.net.URL;
import java.time.LocalDate;

public class TestInfoBean {

    private String id;
    private LocalDate testDate;
    private URL testReservationLink;
    private LocalDate resultsDate;
    private URL infoLink;
    private String place;
    private int testType;

    public TestInfoBean(String id, LocalDate testDate, URL testReservationLink, LocalDate resultsDate, URL infoLink, String place) {
        this.id = id;
        this.testDate = testDate;
        this.testReservationLink = testReservationLink;
        this.resultsDate = resultsDate;
        this.infoLink = infoLink;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public URL getTestReservationLink() {
        return testReservationLink;
    }

    public LocalDate getResultsDate() {
        return resultsDate;
    }

    public URL getInfoLink() {
        return infoLink;
    }

    public String getPlace() {
        return this.place;
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }
}
