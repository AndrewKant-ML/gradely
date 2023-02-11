package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.test_result.TestResutlLazyFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PendingEventDAODB extends DAODBAbstract<PendingEvent> implements PendingEventDAOInterface {
    protected static PendingEventDAOInterface instance;

    private PendingEventDAODB(){ 

    }

    public static synchronized PendingEventDAOInterface getInstance(){
        if (instance == null){
            instance = new PendingEventDAODB();
        }
        return instance;
    }

    @Override
    public List<PendingEvent> getAllPendingEvents(List<PendingEvent> list) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        List<PendingEvent> newList = getListQuery(
                "PENDING_EVENT",
                List.of("id"),
                null,
                list,
                null,
                true
        );
        auxiliaryGetBuilder(newList);

        return newList;
    }

    private void auxiliaryGetBuilder(List<PendingEvent> pendingEvents) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException, UserNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, WrongDegreeCourseCodeException {
        for(PendingEvent p : pendingEvents){
            List<String> recipients = new ArrayList<>();
            String query = String.format("select recipient from PENDING_EVENT_RECIPIENT where pending_event = '%s'", p.id);
            try (Connection connection = DBConnection.getInstance().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    recipients.add(rs.getString("recipient"));
                }
            } catch (SQLException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
            p.setRecipients(recipients);
            String query2 = String.format("select * from PENDING_EVENT_OBJECT where id = %s", p.id);
            try (Connection connection = DBConnection.getInstance().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query2, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    switch (p.type){
                        case GRADE_CONFIRMATION_PENDING :
                        case EXAM_VERBALIZATION_PENDING :
                        case EXAM_VERBALIZED:
                        case GRADE_AUTO_ACCEPTED:
                            p.setObject(ExamLazyFactory.getInstance().getExamById(UUID.fromString(rs.getString("id"))));
                            break;
                        case TEST_RESULT_READY:
                            p.setObject(TestResutlLazyFactory.getInstance().getTestResultById(UUID.fromString(rs.getString("id"))));
                            break;
                        default:

                    }

                }
            } catch (SQLException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
            p.setRecipients(recipients);


        }

    }
    @Override
    public void insert(PendingEvent pendingEvent) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery(
                "PENDING_EVENT",
                List.of(pendingEvent.id,pendingEvent.notified,pendingEvent.type.name())
        );
        auxiliaryInsertBuilder(pendingEvent);
    }

    private void auxiliaryInsertBuilder(PendingEvent pendingEvent) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        for(String s : pendingEvent.getRecipients()){
            insertQuery(
                    "PENDING_EVENT_RECIPIENT",
                    List.of(pendingEvent.id,s)
            );
        }
        switch (pendingEvent.type){
            case GRADE_CONFIRMATION_PENDING :
            case EXAM_VERBALIZATION_PENDING :
            case EXAM_VERBALIZED:
            case GRADE_AUTO_ACCEPTED:
                insertQuery("PENDING_EVENT_OBJECT", List.of(((Exam)pendingEvent.getObject())));
                break;
            case TEST_RESULT_READY:
                System.out.println("");
                break;
            default:

        }
    }

    @Override
    public void cancel(PendingEvent pendingEvent) throws DAOException, PropertyException, ResourceNotFoundException {
        cancelQuery("PENDING_EVENT_OBJECT",List.of("pending_event"),List.of(pendingEvent.id));
        cancelQuery("PENDING_EVENT_RECIPIENT",List.of("pending_event"),List.of(pendingEvent.id));
        cancelQuery("PENDING_EVENT",List.of("pending_event"),List.of(pendingEvent.id));
    }

    @Override
    public void update(PendingEvent pendingEvent){
        System.out.println("Updated");
    }

    @Override
    protected PendingEvent queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, ObjectNotFoundException {
        return new PendingEvent(UUID.fromString(rs.getString("id")),rs.getBoolean("notified"), PendingEventTypeEnum.getPendingEventTypeByName(rs.getString("type")));
    }


    @Override
    protected String setGetListQueryIdentifiersValue(PendingEvent pendingEvent, int valueNumber) throws DAOException {
        return null;
    }


}
