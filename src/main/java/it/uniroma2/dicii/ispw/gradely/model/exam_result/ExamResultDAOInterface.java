package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

public interface ExamResultDAOInterface {
    /**
     * Retrieves a ExamResult with a given email
     *
     * @return a ExamResult object
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     */
    ExamResult getExamResultByStudentAndExam(Student student, Exam exam) throws DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue;

    /**
     * Inserts an object into the DB
     * @param examResult the object to insert
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void insert(ExamResult examResult) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

    /**
     * Deletes an object from the DB
     * @param examResult the object to cancel
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void cancel(ExamResult examResult) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in the DB to its current state
     * @param examResult the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void update(ExamResult examResult) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;


}
