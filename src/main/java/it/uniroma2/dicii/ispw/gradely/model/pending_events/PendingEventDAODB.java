package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


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
                "BIN_TO_UUID(id) AS id, notified, type",
                "PENDING_EVENT",
                List.of("id"),
                null,
                list,
                null,
                true
        );
        for(PendingEvent p : newList){
            auxiliaryBuilder(p);
        }
        return newList;
    }

    @Override
    public void insert(PendingEvent pendingEvent) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery(
                "PENDING_EVENT",
                List.of("UUID_TO_BIN("+pendingEvent.id+")",pendingEvent.notified,pendingEvent.type.name())
        );
        auxiliaryBuilder(pendingEvent);
    }

    private void auxiliaryBuilder(PendingEvent pendingEvent) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
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
                insertQuery("PENDING_EVENT_OBJECT", List.of(((Exam)pendingEvent.getObject()).getAppello().value.toString(),String.valueOf(((Exam)pendingEvent.getObject()).getSubjectCourse().getCode().value),((Exam)pendingEvent.getObject()).getSession().name()));
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
        return new PendingEvent(rs.getInt("id"))
    }


    @Override
    protected String setGetListQueryIdentifiersValue(PendingEvent pendingEvent, int valueNumber) throws DAOException {
        return null;
    }


}
