package it.uniroma2.dicii.ispw.gradely.facades;

import it.uniroma2.dicii.ispw.gradely.beans_general.PendingEventBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.use_cases.controllers_general.pending_event.PendingEventController;

import java.util.List;

public class UserFacade {

    private PendingEventController controller;

    public UserFacade() {
        this.controller = new PendingEventController();
    }

    public List<PendingEventBean> retrievePendingEvents(String tokenKey) throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return controller.retrievePendingEvents(tokenKey);
    }
}
