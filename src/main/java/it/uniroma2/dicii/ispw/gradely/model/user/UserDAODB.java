package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserRoleEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAODB extends DAODBAbstract<User> implements UserDAOInterface  {
    private static final   String EMAIL = "email";
    private static final  String CODICE_FISCALE = "codice_fiscale";
    protected static UserDAOInterface instance;

    private UserDAODB() {
        super();
    }

    public static synchronized UserDAOInterface getInstance() {
        if (instance == null) {
            instance = new UserDAODB();
        }
        return instance;
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return getQuery(
                "USER",
                List.of(EMAIL),
                List.of(email),
                null
        );
    }

    @Override
    public User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return getQuery(
                "USER",
                List.of(CODICE_FISCALE),
                List.of(codiceFiscale),
                null
        );
    }




    @Override
    protected User queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        User user = new User(
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString(CODICE_FISCALE),
                rs.getString(EMAIL),
                rs.getString("password"),
                rs.getDate("registration_date").toLocalDate()
        );
        setUserRoleByRoleEnum(user,UserRoleEnum.getUserRoleByType(rs.getInt("role")));
        return user;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(User user, int valueNumber) throws DAOException {
        return null;
    }

    @Override
    public void insert(User user) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery(
                "USER",
                List.of(user.getCodiceFiscale(), user.getName(), user.getSurname(), user.getPassword(), Date.valueOf(user.getRegistrationDate()), user.getEmail(),user.getRole().getRoleEnumType().type)
        );
    }

    @Override
    public void delete(User user) throws DAOException, PropertyException, ResourceNotFoundException {
        deleteQuery(
                "USER",
                List.of(CODICE_FISCALE),
                List.of(user.getCodiceFiscale())
        );
    }

    @Override
    public void update(User user) throws PropertyException, ResourceNotFoundException, DAOException, MissingAuthorizationException {
        updateQuery(
                "USER",
                List.of( "name", "surname", "password", "registration_date", EMAIL, "role"),
                List.of(user.getName(),user.getSurname(),user.getPassword(),Date.valueOf(user.getRegistrationDate()),user.getEmail(),user.getRole().getRoleEnumType().type),
                List.of(CODICE_FISCALE),
                List.of(user.getCodiceFiscale()));
    }

    /**
     * Set the user role by a given role value
     *
     * @param user the User whose role has to be set
     * @param role the role to be set ot the User
     * @throws UnrecognizedRoleException thrown if the role value cannot be cast to any enum value
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException     thrown if the given User cannot be found
     */
    protected void setUserRoleByRoleEnum(User user, UserRoleEnum role) throws UnrecognizedRoleException, DAOException, UserNotFoundException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue, PropertyException, ResourceNotFoundException {
        switch (role) {
            case STUDENT -> user.setRole(StudentLazyFactory.getInstance().getStudentByUser(user));
            case PROFESSOR -> user.setRole(ProfessorLazyFactory.getInstance().getProfessorByUser(user));
            case SECRETARY -> user.setRole(SecretaryLazyFactory.getInstance().getSecretaryByUser(user));
            default -> throw new UnrecognizedRoleException(ExceptionMessagesEnum.UNRECOGNIZED_ROLE.message);
        }
    }
}
