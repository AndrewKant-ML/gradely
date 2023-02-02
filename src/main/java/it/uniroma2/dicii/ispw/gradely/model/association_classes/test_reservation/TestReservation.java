package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation;

import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;

import java.time.LocalDate;

public class TestReservation {

    private Student student;

    private Test test;

    private LocalDate reservationDate;

    public TestReservation(Student student, Test test){
        this.student = student;
        this.test = test;
        this.reservationDate = LocalDate.now();
    }

    public Student getStudent(){
        return student;
    }

    public Test getTestInfo(){
        return test;
    }

    public LocalDate getReservationDate(){
        return reservationDate;
    }
}
