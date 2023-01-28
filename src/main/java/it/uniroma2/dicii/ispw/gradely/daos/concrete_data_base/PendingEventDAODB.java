package it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractPendingEventDAO;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventSingle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum.E4;


public class PendingEventDAODB extends AbstractPendingEventDAO {
    private List<PendingEvent> pendingEvents;

    private PendingEventDAODB(){ //TODO implementare costruttore vero
        pendingEvents = new ArrayList<PendingEvent>();
        pendingEvents.add(new PendingEventSingle(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it"),E4,"prova test prova"));
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

}
