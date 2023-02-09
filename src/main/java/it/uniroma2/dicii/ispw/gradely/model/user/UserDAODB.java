package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserRoleEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAODB extends DAODBAbstract<User> implements UserDAOInterface  {
    protected static UserDAOInterface instance;

    private static final Logger logger = Logger.getLogger(UserDAODB.class.getName());

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
    public User getUserByEmail(String email) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException {
        return getQuery("STUDENT", List.of("codice_fiscale", "name", "surname", "password", "registration_date", "email", "role"), List.of("email"), List.of(email), null);
    }


    @Override
    public User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException {
        return getQuery("STUDENT", List.of("codice_fiscale", "name", "surname", "password", "registration_date", "email", "role"), List.of("codice_fiscale"), List.of(codiceFiscale), null);
    }


    @Override
    protected User getQueryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException {
        User user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("codice_fiscale"),rs.getString("email"), rs.getString("password"), rs.getDate("registration_date").toLocalDate());
        setUserRoleByRoleEnum(user,UserRoleEnum.getUserRoleByType(rs.getInt("role")));
        return user;
    }

    @Override
    protected String getListQueryIdentifierValue(User user, int valueNumber) throws DAOException {
        return null;
    }


    @Override
    public void insert(User user) throws DAOException, PropertyException, ResourceNotFoundException {
        insertQuery("USER", List.of("codice_fiscale", "name", "surname", "password", "registration_date", "email", "role"), user);
    }

    @Override
    public void cancel(User user) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery("USER",List.of("codice_fiscale"), List.of(user.getCodiceFiscale()));
    }

    @Override
    public void update(User user) throws PropertyException, ResourceNotFoundException, DAOException, MissingAuthorizationException {
        updateQuery("USER", List.of( "name", "surname", "password", "registration_date", "email", "role"),List.of("codice_fiscale"), List.of(user.getCodiceFiscale()), user);
    }
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, User user) throws SQLException, MissingAuthorizationException {
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        stmt.setString(3, user.getPassword());
        stmt.setDate(4, Date.valueOf(user.getRegistrationDate()));
        stmt.setString(5, user.getEmail());
        stmt.setInt(6, user.getRole().getRoleEnumType().type);
    }

    protected void setInsertQueryParametersValue(PreparedStatement stmt, User user) throws SQLException {
        stmt.setString(1,user.getCodiceFiscale());
        stmt.setString(2, user.getName());
        stmt.setString(3, user.getSurname());
        stmt.setString(4, user.getPassword());
        stmt.setDate(5, Date.valueOf(user.getRegistrationDate()));
        stmt.setString(6, user.getEmail());
        try{
            stmt.setInt(7, user.getRole().getRoleEnumType().type);
        }catch (MissingAuthorizationException e){
            logger.log(Level.SEVERE, "User with no role");
        }
    }
    protected void setQueryIdentifiers(PreparedStatement stmt, List<Object> identifiersValues, String queryType) throws SQLException{
        if(!queryType.equals(""))
        stmt.setString(1, (String) identifiersValues.get(0));
    }

    @Override
    protected User getListQueryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, MissingAuthorizationException, UnrecognizedRoleException {
        return null;
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
    protected void setUserRoleByRoleEnum(User user, UserRoleEnum role) throws UnrecognizedRoleException, DAOException, UserNotFoundException {
        switch (role) {
            case STUDENT -> user.setRole(StudentLazyFactory.getInstance().getStudentByUser(user));
            case PROFESSOR -> user.setRole(ProfessorLazyFactory.getInstance().getProfessorByUser(user));
            case SECRETARY -> user.setRole(SecretaryLazyFactory.getInstance().getSecretaryByUser(user));
            default -> throw new UnrecognizedRoleException(ExceptionMessagesEnum.UNRECOGNIZED_ROLE.message);
        }
    }
}
