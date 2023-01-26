package it.uniroma2.dicii.ispw.gradely.daos;

import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.CCD;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CCDDAO {
    private static CCDDAO instance;
    private List<CCD> CCDs;

    private CCDDAO(){ //TODO implementare costruttore vero
        CCDs = new ArrayList<CCD>();
        CCDs.add(new CCD(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it")));
    }

    public static CCDDAO getInstance(){
        if (instance == null) {
            instance = new CCDDAO();
        }
        return instance;
    }

    public CCD getCCDByUser(User user) {
        for(CCD c : CCDs){
            if(c.getUser().equals(user)) {
                return c; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }
}