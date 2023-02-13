package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PendingEvent {
    protected UUID id;
    protected PendingEventTypeEnum type;
    private List<String> recipients;
    protected Boolean notified;
    protected Object object;

    protected PendingEvent(List<String> recipients, PendingEventTypeEnum type, Object object) {
        //only for brand-new PendingEvents, id is generated
        this.id = UUID.randomUUID();
        this.type = type;
        this.recipients = recipients;
        this.notified = false;
        this.object = object;
    }
    protected PendingEvent(UUID id, Boolean notified, PendingEventTypeEnum type) {
        //only for existing PendingEvents, id is not generated
        this.id = id;
        this.type = type;
        this.notified = notified;
    }

    protected PendingEvent(UUID id,List<String> recipients, PendingEventTypeEnum type, Boolean notified, Object object) {
        //only for existing PendingEvents, id is not generated
        this.id = id;
        this.type = type;
        this.recipients = recipients;
        this.notified = notified;
        this.object = object;
    }

    public Boolean isForUser(User user){
        return (this.recipients.contains(user.getCodiceFiscale()));
    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public PendingEventTypeEnum getType(){
        return type;
    }

    public void setType(PendingEventTypeEnum type){
        this.type = type;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Boolean getNotified(){
        return notified;
    }

    public void setNotified(Boolean notified){
        this.notified = notified;
    }

    public Object getObject(){
        return object;
    }

    public void setObject(Objects object){
        this.object = object;
    }
}
