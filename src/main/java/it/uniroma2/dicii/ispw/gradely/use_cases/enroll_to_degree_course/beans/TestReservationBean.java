package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans;

import java.util.UUID;

public class TestReservationBean {

    private final UUID reservationCode;
    private final UUID testId;

    public TestReservationBean(UUID testId){
        this.testId = testId;
        reservationCode = UUID.randomUUID();
    }

    public UUID getReservationCode(){
        return reservationCode;
    }
}
