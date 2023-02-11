package it.uniroma2.dicii.ispw.gradely.model.exam;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.UUID;

public interface ExamDAOInterface {

    Exam getExamByAppelloAndSubjectCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) throws DAOException;

    Exam getExamById(UUID id);

    /**
     * Inserts an object into the DB
     * @param exam the object to insert
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void insert(Exam exam) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

    /**
     * Deletes an object from the DB
     * @param exam the object to delete
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void delete(Exam exam) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in the DB to its current state
     * @param exam the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void update(Exam exam) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

}
