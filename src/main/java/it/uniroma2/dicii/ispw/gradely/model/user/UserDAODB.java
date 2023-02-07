package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserRoleEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAODB extends UserDAOAbstract {

    private static final Logger logger = Logger.getLogger(UserDAODB.class.getName());

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
    User getUserByEmail(String email) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException {
        String query = "select codice_fiscale,name,surname,email,password,registration_date,role from USER where email='%s'";
        query = String.format(query, email);
        return getUserFromQuery(query);
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
    User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException {
        String query = "select codice_fiscale, name, surname, password, registration_date, email, role from USER where codice_fiscale='%s';";
        query = String.format(query, codiceFiscale);
        return getUserFromQuery(query);
    }

    /**
     * Retrieves User data and return a User object
     *
     * @param query the query to be executed
     * @return a User object
     * @throws UserNotFoundException thrown if the User cannot be found
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     */
    private User getUserFromQuery(String query) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException {
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
        } catch (SQLException | UnrecognizedRoleException e) {
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
    public void insert(User user) throws DAOException, PropertyException, ResourceNotFoundException {
        String query = "insert into USER (codice_fiscale, name, surname, password, registration_date, email, role) values (?, ?, ?, ?, ?, ?, ?)";
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                setQueryParameters(stmt, user);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    @Override
    public void cancel(User user) throws DAOException, PropertyException, ResourceNotFoundException {
        String query = "delete from USER where codice_fiscale = ?";
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1,user.getCodiceFiscale());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    @Override
    public void update(User user) throws PropertyException, ResourceNotFoundException, DAOException {
        String query = "update USER set codice_fiscale = ?, name = ?, surname = ?, password = ?, registration_date = ?, email = ?, role = ? where codice_fiscale = ?";
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                setQueryParameters(stmt, user);
                stmt.setString(8,user.getCodiceFiscale());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    private void setQueryParameters(PreparedStatement stmt, User user) throws SQLException {
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
}
