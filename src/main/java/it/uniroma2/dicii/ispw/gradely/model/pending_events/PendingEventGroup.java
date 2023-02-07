package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class PendingEventGroup extends AbstractPendingEvent {
    private List<String> codiceFiscales;

    public PendingEventGroup(List<String> codiceFiscales, PendingEventTypeEnum type, Object object) throws DAOException {
        super(type, object);
        this.codiceFiscales = codiceFiscales;
        super.notified = false;
    }

    @Override
    public Boolean isForUser(User user){
        return (this.codiceFiscales.contains(user.getCodiceFiscale()));
    }

    public List<String> getUsersCodiceFiscales(){
        return codiceFiscales;
    }

    public void setUsersCodiceFiscales(List<String> codiceFiscales){
        this.codiceFiscales = codiceFiscales;
    }

}
