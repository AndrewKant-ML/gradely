package it.uniroma2.dicii.ispw.gradely.enums;

public enum PendingEventTypeEnum {
    EVENT_1("pending grade notification, system is waiting confirmation from student"),
    EVENT_2("The exam was marked accepted by the system due to lack of confirmation by the student"),
    EVENT_3("The following exam grades are waitin for validity verification"),
    EVENT_PROVA("prova evento"),
    EVENT_4("The exam verbale was protocolized by secretary"),
    EVENT_5("");

    public final String message;

    private PendingEventTypeEnum(String message){
        this.message = message;
    }
}
