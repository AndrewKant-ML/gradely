package it.uniroma2.dicii.ispw.gradely.dao_interface;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DAOInterface<T>{
    void insert(T t) throws DAOException, PropertyException, ResourceNotFoundException;
    void cancel(T t) throws DAOException, PropertyException, ResourceNotFoundException;
    void update(T t) throws DAOException, PropertyException, ResourceNotFoundException;
}
