package it.uniroma2.dicii.ispw.gradely.model;

import java.net.URL;
import java.time.LocalDate;

public class TestInfo {

    private DegreeCourse degreeCourse;
    private String ID;
    private LocalDate testDate;
    private URL testReservationLink;
    private LocalDate resultsDate;
    private URL infoLink;
    private String place;

    public DegreeCourse getDegreeCourse() {
        return degreeCourse;
    }

    public void setDegreeCourse(DegreeCourse degreeCourse) {
        this.degreeCourse = degreeCourse;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public URL getTestReservationLink() {
        return testReservationLink;
    }

    public void setTestReservationLink(URL testReservationLink) {
        this.testReservationLink = testReservationLink;
    }

    public LocalDate getResultsDate() {
        return resultsDate;
    }

    public void setResultsDate(LocalDate resultsDate) {
        this.resultsDate = resultsDate;
    }

    public URL getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(URL infoLink) {
        this.infoLink = infoLink;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
