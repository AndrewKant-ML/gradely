package it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractCCDDAO;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.CCD;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class CCDDAODB extends AbstractCCDDAO {
    private static CCDDAODB instance;
    private List<CCD> ccds;

    private CCDDAODB(){ //TODO implementare costruttore vero
        ccds = new ArrayList<CCD>();
        ccds.add(new CCD(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it")));
    }

    public static CCDDAODB getInstance(){
        if (instance == null) {
            instance = new CCDDAODB();
        }
        return instance;
    }

    public CCD getCCDByUser(User user) {
        for(CCD c : ccds){
            if(c.getUser().equals(user)) {
                return c; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }
}