package it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base;

import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.PendingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum.E4;


public class PendingEventDAO {
    private static PendingEventDAO instance;
    private List<PendingEvent> pendingEvents;

    private PendingEventDAO(){ //TODO implementare costruttore vero
        pendingEvents = new ArrayList<PendingEvent>();
        pendingEvents.add(new PendingEvent(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it"),E4,"prova test prova"));
    }

    public static PendingEventDAO getInstance(){
        if (instance == null) {
            instance = new PendingEventDAO();
        }
        return instance;
    }

    public PendingEvent getPendingEventById(UUID id) {
        for(PendingEvent p : pendingEvents){
            if(p.getId().equals(id)) {
                return p; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }
}
