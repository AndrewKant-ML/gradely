package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserRoleEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UserDAOAbstract extends DAOAbstract<User, String> {
    protected static UserDAOAbstract instance;


    protected UserDAOAbstract(){
    }


    abstract User getUserByEmail(String email) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;
    abstract User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;

    abstract void insert(User user) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void cancel(User user) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void update(User user) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;
    abstract void setQueryParameters(PreparedStatement stmt, User user) throws SQLException;

    /**
     * Set the user role by a given role value
     *
     * @param user the User whose role has to be set
     * @param role the role to be set ot the User
     * @throws UnrecognizedRoleException thrown if the role value cannot be cast to any enum value
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException     thrown if the given User cannot be found
     */
    protected void setUserRoleByRoleEnum(User user, UserRoleEnum role) throws UnrecognizedRoleException, DAOException, UserNotFoundException {
        switch (role) {
            case STUDENT -> user.setRole(StudentLazyFactory.getInstance().getStudentByUser(user));
            case PROFESSOR -> user.setRole(ProfessorLazyFactory.getInstance().getProfessorByUser(user));
            case SECRETARY -> user.setRole(SecretaryLazyFactory.getInstance().getSecretaryByUser(user));
            default -> throw new UnrecognizedRoleException(ExceptionMessagesEnum.UNRECOGNIZED_ROLE.message);
        }
    }
}
