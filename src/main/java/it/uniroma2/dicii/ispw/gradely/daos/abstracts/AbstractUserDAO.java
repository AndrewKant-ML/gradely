package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.daos.factories.UserDAO;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUserDAO {

    private static UserDAO instance;
    private List<User> registeredUsers;

    private UserDAO(){ //TODO implementare costruttore vero
        registeredUsers = new ArrayList<User>();
        registeredUsers.add(new User("mario","rossi","RSSMRI01A02H501Y","m.rossi@uniroma2.it","PWD.difficilissim4"));
    }

    public static UserDAO getInstance(){
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public User getUser(String email) {
        for(User u : registeredUsers){
            if(u.getEmail().equals(email)) {
                return u; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }




    /*private static WidgetFactory me = null;

	protected WidgetFactory(){
	}

	public static synchronized WidgetFactory getWidgetFactory(){
		if ( me == null ){
			String laf = System.getProperty("abstractFactoryExample.look_and_feel");

			switch (SupportedLookAndFeels.valueOf(laf)) {
			case Motif :
					me = new MotifWidgetFactory();
				break;
			case PM :
				    me = new PMWidgetFactory();
				break;
			}
		}
		return me;
	}

	public abstract Window createWindow();
	public abstract ScrollBar createScrollBar();*/


}
