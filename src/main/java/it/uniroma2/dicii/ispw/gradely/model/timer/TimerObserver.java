package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;

public interface TimerObserver {
    void timeIsUp(AbstractTimer timer) throws WrongTimerTypeException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, MissingAuthorizationException, ObjectNotFoundException, UnrecognizedRoleException, WrongListQueryIdentifierValue, WrongDegreeCourseCodeException;
}
