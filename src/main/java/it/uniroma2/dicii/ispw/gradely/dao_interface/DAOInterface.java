package it.uniroma2.dicii.ispw.gradely.dao_interface;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;

import java.util.List;

public interface DAOInterface<T>{
    void insert(T t) throws DAOException;
    void cancel(T t) throws DAOException;
    void update(T t) throws DAOException;
    List<T> refresh(List<T> ts) throws DAOException;
}
