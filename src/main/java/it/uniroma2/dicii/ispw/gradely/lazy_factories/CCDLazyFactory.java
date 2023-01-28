package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractCCDDAO;
import it.uniroma2.dicii.ispw.gradely.model.CCD;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class CCDLazyFactory {
    private static CCDLazyFactory instance;
    private List<CCD> ccds;

    private CCDLazyFactory(){
        ccds = new ArrayList<CCD>();
    }

    public static CCDLazyFactory getInstance(){
        if (instance == null) {
            instance = new CCDLazyFactory();
        }
        return instance;
    }

    public CCD getCCDByUser(User user) {
        for(CCD c : ccds){
            if(c.getUser().equals(user)) {
                return c; //TODO implementare exception
            }
        }
        return AbstractCCDDAO.getInstance().getCCDByUser(user); //TODO implementare exception
    }
}
