package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.util.UUID;

public abstract class TimerObserver {
    private UUID id;

    protected TimerObserver() {
        this.id = UUID.randomUUID();
    }

    protected TimerObserver(UUID id) {
        this.id = id;
    }

    public abstract void timeIsUp(AbstractTimer timer) throws WrongTimerTypeException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, MissingAuthorizationException, ObjectNotFoundException, UnrecognizedRoleException, WrongListQueryIdentifierValue, WrongDegreeCourseCodeException;
}
