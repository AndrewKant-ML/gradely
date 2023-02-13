package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public interface AbstractProfessorDAO {


    /**
     * Retrieve all Professor's data of a given User
     *
     * @param user the User whose Professor's data have to be retrieved
     * @return a Professor object
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException     thrown if the given User has not a Professor role
     * @throws PropertyException thrown if errors occur while loading db connection properties OR thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue;

    List<Professor> getProfessorsByDipartimento(DipartimentoEnum dipartimento, List<Professor> professors) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException;
}
