package it.uniroma2.dicii.ispw.gradely.model.test;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;

public interface TestDAOAbstract{


    abstract void saveTestInfo(Test test) throws PropertyException, ResourceNotFoundException, DAOException;

    abstract Test getTestById(String id) throws PropertyException, ResourceNotFoundException, DAOException, ObjectNotFoundException, WrongDegreeCourseCodeException;
}
