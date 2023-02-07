package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public class PendingEventSingle extends AbstractPendingEvent {
    private String codiceFiscale;

    public PendingEventSingle(String codiceFiscale, PendingEventTypeEnum type, Object object) throws DAOException {
        super(type, object);
        this.codiceFiscale = codiceFiscale;
        this.notified = false;
    }

    @Override
    public Boolean isForUser(User user) {
        return (this.codiceFiscale.equals(user.getCodiceFiscale()));
    }
    public String getUserCodiceFiscale(){
        return codiceFiscale;
    }

    public void setUserCodiceFiscale(String codiceFiscale){
        this.codiceFiscale = codiceFiscale;
    }

}
