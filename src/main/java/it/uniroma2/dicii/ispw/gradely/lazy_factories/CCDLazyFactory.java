package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.CCDDAO;
import it.uniroma2.dicii.ispw.gradely.model.CCD;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class CCDLazyFactory {
    private static CCDLazyFactory instance;
    private List<CCD> CCDs;

    private CCDLazyFactory(){
        CCDs = new ArrayList<CCD>();
    }

    public static CCDLazyFactory getInstance(){
        if (instance == null) {
            instance = new CCDLazyFactory();
        }
        return instance;
    }

    public CCD getCCDByUser(User user) {
        for(CCD c : CCDs){
            if(c.getUser().equals(user)) {
                return c; //TODO implementare exception
            }
        }
        return CCDDAO.getInstance().getCCDByUser(user); //TODO implementare exception
    }
}
