package it.uniroma2.dicii.ispw.gradely.dao_interface;

import java.util.List;

public interface DAOInterface<T>{
    void insert(T t);
    void update(T t);
    List<T> refresh(List<T> ts);
}
