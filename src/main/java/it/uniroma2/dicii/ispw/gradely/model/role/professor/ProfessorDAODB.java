package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProfessorDAODB extends DAODBAbstract<Professor> implements AbstractProfessorDAO {
    private static final String TABLE = "PROFESSOR";
    private static final String CODICE_FISCALE = "codice_fiscale";
    private static final String DIPARTIMENTO = "dipartimento";

    protected static AbstractProfessorDAO instance;

    private ProfessorDAODB() {

    }

    public static synchronized AbstractProfessorDAO getInstance() {
        if (instance == null) {
            instance = new ProfessorDAODB();
        }
        return instance;
    }

    @Override
    public Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return getQuery(
                TABLE,
                List.of(CODICE_FISCALE),
                List.of(user.getCodiceFiscale()),
                List.of(user)
        );
    }

    @Override
    public List<Professor> getProfessorsByDipartimento(DipartimentoEnum dipartimento, List<Professor> professors) throws UserNotFoundException, DAOException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return getListQuery(
                TABLE,
                List.of(DIPARTIMENTO),
                List.of(dipartimento.value),
                professors,
                List.of(dipartimento),
                false
        );
    }

    @Override
    public void insert(Professor professor) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery(
                TABLE,
                List.of(professor.getCodiceFiscale(), professor.getMatricola(), professor.getDipartimento().value)
        );
    }

    @Override
    public void delete(Professor professor) throws DAOException, PropertyException, ResourceNotFoundException {
        deleteQuery(
                TABLE,
                List.of(CODICE_FISCALE),
                List.of(professor.getCodiceFiscale())
        );
    }

    @Override
    public void update(Professor professor) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        updateQuery(
                TABLE,
                List.of("matricola", "dipartimento"),
                List.of(professor.getMatricola(),professor.getDipartimento().value),
                List.of(CODICE_FISCALE),
                List.of(professor.getCodiceFiscale())
        );
    }

    @Override
    protected Professor queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, MissingAuthorizationException, UnrecognizedRoleException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue, ObjectNotFoundException {
        Object o = objects.get(0);
        Professor professor;
        if (o instanceof User)
            professor = new Professor(
                    (User) o,
                    rs.getString("matricola"),
                    DipartimentoEnum.getDipartimentoByValue(rs.getInt("dipartimento"))
            );
        else
            professor = new Professor(
                    UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString(CODICE_FISCALE)),
                    rs.getString("matricola"),
                    (DipartimentoEnum) o
            );
        try {
            professor.setCoordinatedCourse(DegreeCourseLazyFactory.getInstance().getDegreeCourseByCoordinatore(professor));
        } catch (ObjectNotFoundException e) {
            professor.setCoordinatedCourse(null);
        }
        try {
            professor.setCourseAssignments(SubjectCourseAssignmentLazyFactory.getInstance().getCourseAssignmentsByProfessor(professor));
        } catch (ObjectNotFoundException e) {
            professor.setCourseAssignments(null);
        }
        return professor;
    }

    @Override
    protected String setGetListQueryIdentifiersValue(Professor professor, int valueNumber) throws DAOException {
        return null;
    }

}
