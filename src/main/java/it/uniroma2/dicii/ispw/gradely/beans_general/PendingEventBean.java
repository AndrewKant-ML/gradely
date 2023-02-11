package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;

import java.util.UUID;

public class PendingEventBean {

    private UUID id;
    private PendingEventTypeEnum type;
    private Boolean notified;

    public PendingEventBean(UUID id, PendingEventTypeEnum type, Boolean notified) {
        this.id = id;
        this.type = type;
        this.notified = notified;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PendingEventTypeEnum getType() {
        return type;
    }

    public void setType(PendingEventTypeEnum type) {
        this.type = type;
    }

    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }
}
