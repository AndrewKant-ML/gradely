package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamLazyFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TimerDAODB extends DAODBAbstract<AbstractTimer> implements TimerDAOInterface {
    private static final String TIMER = "TIMER";
    
    protected static TimerDAOInterface instance;

    private TimerDAODB(){
        super();
    }

    public static synchronized TimerDAOInterface getInstance(){
        if (instance == null){
            instance = new TimerDAODB();
        }
        return instance;
    }

    public List<AbstractTimer> getAllTimers(List<AbstractTimer> list) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        List<AbstractTimer> newList = getListQuery(
                TIMER,
                List.of("id"),
                null,
                list,
                null,
                true
        );
        auxiliaryGetBuilder(newList);
        return newList;
    }
    private void auxiliaryGetBuilder(List<AbstractTimer> timers) throws DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        for(AbstractTimer t : timers){

            String query2 = String.format("select id from TIMER_OBJECT where timer_id = '%s'", t.id);
            try (Connection connection = DBConnection.getInstance().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query2, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    t.setObject(ExamLazyFactory.getInstance().getExamById(UUID.fromString(rs.getString("id"))));
                    t.castToTestResultTimer();
                }
            } catch (SQLException | WrongTimerTypeException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
        }

    }
    private void setTimerObservers(AbstractTimer t) throws PropertyException, ResourceNotFoundException, DAOException {
        List<String> observers = new ArrayList<>();
        String query = String.format("select id from TIMER_OBSERVER where timer_id = '%s'", t.id);
        queryAndAddToList(query,observers);
        t.setObservers(observers);
    }

    @Override
    public void insert(AbstractTimer timer) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery(TIMER,List.of(timer.getId(), Date.valueOf(timer.getExpiration())));
    }

    @Override
    public void delete(AbstractTimer timer) throws DAOException, PropertyException, ResourceNotFoundException {
        deleteQuery(TIMER, List.of("id"),List.of(timer.getId().toString()));
    }

    @Override
    public void update(AbstractTimer timer) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        updateQuery(TIMER,List.of("expiration_date"),List.of(Date.valueOf(timer.getExpiration())),List.of("id"),List.of(timer.getId().toString()));
    }

    @Override
    protected AbstractTimer queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return null;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(AbstractTimer timer, int valueNumber) throws DAOException, WrongListQueryIdentifierValue {
        return null;
    }


}
