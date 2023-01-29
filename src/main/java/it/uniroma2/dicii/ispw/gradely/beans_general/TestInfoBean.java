package it.uniroma2.dicii.ispw.gradely.beans_general;

import java.net.URL;
import java.time.LocalDate;

public class TestInfoBean {

    private String ID;
    private LocalDate testDate;
    private URL testReservationLink;
    private URL infoLink;
    private String place;

    public TestInfoBean(String ID, LocalDate testDate, URL testReservationLink, URL infoLink, String place) {
        this.ID = ID;
        this.testDate = testDate;
        this.testReservationLink = testReservationLink;
        this.infoLink = infoLink;
        this.place = place;
    }

    public String getID() {
        return ID;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public URL getTestReservationLink() {
        return testReservationLink;
    }

    public URL getInfoLink() {
        return infoLink;
    }

    public String getPlace() {
        return this.place;
    }
}
