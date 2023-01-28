package it.uniroma2.dicii.ispw.gradely.model;

import java.net.URL;
import java.time.LocalDate;

public class TestInfo {

    private LocalDate testDate;
    private URL testLink;
    private LocalDate resultsDate;
    private URL infoLink;

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public URL getTestLink() {
        return testLink;
    }

    public void setTestLink(URL testLink) {
        this.testLink = testLink;
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
}
