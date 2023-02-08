package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public abstract class AbstractProfessorDAO implements DAODBAbstract<Professor> {
    protected static AbstractProfessorDAO instance;

    protected AbstractProfessorDAO() {
    }

    /**
     * Retrieve all Professor's data of a given User
     *
     * @param user the User whose Professor's data have to be retrieved
     * @return a Professor object
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException     thrown if the given User has not a Professor role
     * @throws PropertyException thrown if errors occur while loading db connection properties OR thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    public abstract Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException;

    protected void setProfessorData(Professor professor) throws DAOException, UserNotFoundException {
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
    }
}
