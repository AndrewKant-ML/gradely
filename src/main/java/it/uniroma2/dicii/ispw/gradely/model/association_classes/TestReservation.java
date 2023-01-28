package it.uniroma2.dicii.ispw.gradely.model.association_classes;

import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.TestInfo;

import java.time.LocalDate;

public class TestReservation {

    private Student student;

    private TestInfo testInfo;

    private LocalDate reservationDate;

    public TestReservation(Student student, TestInfo testInfo) {
        this.student = student;
        this.testInfo = testInfo;
        this.reservationDate = LocalDate.now();
    }

    public Student getStudent() {
        return student;
    }

    public TestInfo getTestInfo() {
        return testInfo;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }
}
