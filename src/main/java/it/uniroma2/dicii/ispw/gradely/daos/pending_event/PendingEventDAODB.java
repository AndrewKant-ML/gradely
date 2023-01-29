package it.uniroma2.dicii.ispw.gradely.daos.pending_event;

import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventSingle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum.EVENT_PROVA;


public class PendingEventDAODB extends AbstractPendingEventDAO {

    private PendingEventDAODB(){ //TODO implementare costruttore vero
        pendingEvents = new ArrayList<PendingEvent>();
        pendingEvents.add(new PendingEventSingle(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it"), EVENT_PROVA,"prova object"));
    }

    public static AbstractPendingEventDAO getInstance(){
        if (instance == null) {
            instance = new PendingEventDAODB();
        }
        return instance;
    }

    @Override
    public PendingEvent getPendingEventById(UUID id) {
        for(PendingEvent p : pendingEvents){
            if(p.getId().equals(id)) {
                return p; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }
    @Override
    public void update(PendingEvent pendingEvent){
        System.out.println("Updated");
    }

    @Override
    public List<PendingEvent> refresh(List<PendingEvent> pendingEvents) {
        return null;
    }

}
