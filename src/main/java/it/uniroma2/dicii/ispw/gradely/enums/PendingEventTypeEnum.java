package it.uniroma2.dicii.ispw.gradely.enums;

public enum PendingEventTypeEnum {
    GRADE_CONFIRMATION_PENDING("Pending grade notification, system is waiting confirmation from student"),
    GRADE_AUTO_ACCEPTED("The exam was marked accepted by the system due to lack of confirmation by the student"),
    EXAM_VERBALIZATION_PENDING("The following exam grades are waiting for validity verification"),
    EXAM_VERBALIZED("The exam verbale was protocolized by secretary"),
    TEST_RESULT_READY("Test results are available."),
    EVENT_PROVA("prova evento");

    public final String message;

    PendingEventTypeEnum(String message){
        this.message = message;
    }
}
