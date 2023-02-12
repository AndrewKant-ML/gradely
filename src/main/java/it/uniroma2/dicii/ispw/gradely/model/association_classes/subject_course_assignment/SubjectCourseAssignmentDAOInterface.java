package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public interface SubjectCourseAssignmentDAOInterface {
    public abstract List<SubjectCourseAssignment> getCourseAssignmentsBySubjectCourse(SubjectCourse course, List<SubjectCourseAssignment> exclusions) throws DAOException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue, UserNotFoundException;

    public abstract List<SubjectCourseAssignment> getCourseAssignmentsByProfessor(Professor professor, List<SubjectCourseAssignment> exclusions) throws DAOException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue, UserNotFoundException;

    /**
     * Inserts an object into the DB
     * @param subjectCourseAssignment the object to insert
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void insert(SubjectCourseAssignment subjectCourseAssignment) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

    /**
     * Deletes an object from the DB
     * @param subjectCourseAssignment the object to delete
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void delete(SubjectCourseAssignment subjectCourseAssignment) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in the DB to its current state
     * @param subjectCourseAssignment the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void update(SubjectCourseAssignment subjectCourseAssignment) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;


}
