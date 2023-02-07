package it.uniroma2.dicii.ispw.gradely.model.test;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.net.URL;
import java.time.LocalDate;

public class Test {
    private DegreeCourse degreeCourse;
    private String id;
    private LocalDate testDate;
    private URL testReservationLink;
    private LocalDate resultsDate;
    private URL infoLink;
    private String place;

    protected Test(DegreeCourse degreeCourse, String id, LocalDate testDate, URL testReservationLink, LocalDate resultsDate, URL infoLink, String place){
        this.degreeCourse = degreeCourse;
        this.id = id;
        this.testDate = testDate;
        this.testReservationLink = testReservationLink;
        this.resultsDate = resultsDate;
        this.infoLink = infoLink;
        this.place = place;
    }

    public DegreeCourse getDegreeCourse(){
        return degreeCourse;
    }

    public void setDegreeCourse(DegreeCourse degreeCourse){
        this.degreeCourse = degreeCourse;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public LocalDate getTestDate(){
        return testDate;
    }

    public void setTestDate(LocalDate testDate){
        this.testDate = testDate;
    }

    public URL getTestReservationLink(){
        return testReservationLink;
    }

    public void setTestReservationLink(URL testReservationLink){
        this.testReservationLink = testReservationLink;
    }

    public LocalDate getResultsDate(){
        return resultsDate;
    }

    public void setResultsDate(LocalDate resultsDate){
        this.resultsDate = resultsDate;
    }

    public URL getInfoLink(){
        return infoLink;
    }

    public void setInfoLink(URL infoLink){
        this.infoLink = infoLink;
    }

    public String getPlace(){
        return place;
    }

    public void setPlace(String place){
        this.place = place;
    }
}
