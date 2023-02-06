package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserRoleEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UnrecognizedRoleException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAODB extends UserDAOAbstract {

    private UserDAODB() {
        super();
    }

    public static synchronized UserDAOAbstract getInstance() {
        if (instance == null) {
            instance = new UserDAODB();
        }
        return instance;
    }

    /**
     * Retrieves a User with a given email
     *
     * @param email the User's email
     * @return a User object
     * @throws UserNotFoundException thrown if the User cannot be found
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     */
    User getUserByEmail(String email) throws UserNotFoundException, DAOException {
        String query = "select codice_fiscale,name,surname,email,password,registration_date,role from USER where email='%s'";
        query = String.format(query, email);
        return queryUserData(query);
    }

    /**
     * Retrieves a User with a given codice fiscale
     *
     * @param codiceFiscale the User's codice fiscale
     * @return a User object
     * @throws UserNotFoundException thrown if the User cannot be found
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     */
    @Override
    User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException {
        String query = "select codice_fiscale, name, surname, password, registration_date, email, role from USER U where U.codice_fiscale='%s';";
        query = String.format(query, codiceFiscale);
        return queryUserData(query);
    }

    /**
     * Retrieves User data and return a User object
     *
     * @param query the query to be executed
     * @return a User object
     * @throws UserNotFoundException thrown if the User cannot be found
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     */
    private User queryUserData(String query) throws UserNotFoundException, DAOException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    User user = new User(
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("codice_fiscale"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getDate("registration_date").toLocalDate()
                    );
                    UserRoleEnum role = UserRoleEnum.getUserRoleByType(rs.getInt("role"));
                    if (role != null) {
                        setUserRoleByRoleEnum(user, role);
                        return user;
                    } else
                        throw new UnrecognizedRoleException(ExceptionMessagesEnum.UNRECOGNIZED_ROLE.message);
                } else
                    throw new UserNotFoundException(ExceptionMessagesEnum.USER_NOT_FOUND.message);
            }
        } catch (SQLException | IOException | UnrecognizedRoleException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
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
    private void setUserRoleByRoleEnum(User user, UserRoleEnum role) throws UnrecognizedRoleException, DAOException, UserNotFoundException {
        switch (role) {
            case STUDENT -> user.setRole(StudentLazyFactory.getInstance().getStudentByUser(user));
            case PROFESSOR -> user.setRole(ProfessorLazyFactory.getInstance().getProfessorByUser(user));
            case SECRETARY -> user.setRole(SecretaryLazyFactory.getInstance().getSecretaryByUser(user));
            default -> throw new UnrecognizedRoleException(ExceptionMessagesEnum.UNRECOGNIZED_ROLE.message);
        }
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void cancel(User user) {

    }

    @Override
    public void update(User user){

    }

    @Override
    public List<User> refresh(List<User> users){
        return null;
    }


}
