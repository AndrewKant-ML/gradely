package it.uniroma2.dicii.ispw.gradely.instances_management_abstracts;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.util.List;

public abstract class LazyFactoryAbstract <T>{
    protected List<T> factoryObjects;

    /**
     * Deletes an object from System and DB
     * @param t the object to delete
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected abstract void delete(T t) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in DB to its current state
     * @param t the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected abstract void update(T t) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

}
