package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;
import it.uniroma2.dicii.ispw.gradely.model.test_result.TestResutlLazyFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.UUID;

import static it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum.GRADE_CONFIRMATION_PENDING;

public class TimerDAODB extends DAODBAbstract<AbstractTimer> implements TimerDAOInterface {
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
                "TIMER",
                List.of("id"),
                null,
                list,
                null,
                true
        );
        auxiliaryGetBuilder(newList);
        return newList;
    }
    private void auxiliaryGetBuilder(List<AbstractTimer> timers) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException, UserNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException {
        for(AbstractTimer t : timers){
            List<String> observers = new ArrayList<>();
            String query = String.format("select id from TIMER_OBSERVER where timer_id = '%s'", t.id);
            try (Connection connection = DBConnection.getInstance().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    observers.add(rs.getString("recipient"));
                }
            } catch (SQLException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
            t.setObservers(observers);
            String query2 = String.format("select id from TIMER_OBJECT where timer_id = '%s'", t.id);
            try (Connection connection = DBConnection.getInstance().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query2, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    try{
                        t.castToExamConfirmationTimer();
                        t.setObject(ExamLazyFactory.getInstance().getExamById(UUID.fromString(rs.getString("id"))));
                    } catch (WrongTimerTypeException e) {
                        try {
                            t.castToTestResultTimer();
                        } catch (WrongTimerTypeException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                }
            } catch (SQLException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
        }

    }

    @Override
    public void insert(AbstractTimer timer) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery("TIMER",List.of(timer.getId(), Date.valueOf(timer.getExpiration())));
    }

    @Override
    public void cancel(AbstractTimer timer) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery("TIMER", List.of("id"),List.of(timer.getId().toString()));
    }

    @Override
    public void update(AbstractTimer timer) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        updateQuery("TIMER",List.of("expiration_date"),List.of(Date.valueOf(timer.getExpiration())),List.of("id"),List.of(timer.getId().toString()));
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
