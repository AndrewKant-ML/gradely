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

    User getUserByEmail(String email) throws UserNotFoundException, DAOException {
        String query = "select codice_fiscale,name,surname,email,password,registration_date,role from USER where email='%s'";
        query = String.format(query, email);
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
                        switch (role) {
                            case STUDENT -> user.setRole(StudentLazyFactory.getInstance().getStudentByUser(user));
                            case PROFESSOR -> user.setRole(ProfessorLazyFactory.getInstance().getProfessorByUser(user));
                            case SECRETARY -> user.setRole(SecretaryLazyFactory.getInstance().getSecretaryByUser(user));
                            default ->
                                    throw new UnrecognizedRoleException(ExceptionMessagesEnum.UNRECOGNIZED_ROLE.message);
                        }
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
