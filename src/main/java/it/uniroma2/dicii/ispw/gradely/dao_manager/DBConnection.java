package it.uniroma2.dicii.ispw.gradely.dao_manager;

import it.uniroma2.dicii.ispw.gradely.model.exam.ExamLazyFactory;

public class DBConnection {
    private static DBConnection instance;

    public static synchronized DBConnection getInstance(){
        if (instance == null){
            instance = new DBConnection();
        }
        return instance;
    }
}
