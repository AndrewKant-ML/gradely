package it.uniroma2.dicii.ispw.gradely.model.timer;

public class TimerDAOFS extends TimerDAOInterface {
    protected static TimerDAOInterface instance;

    private TimerDAOFS(){
        super();
    }

    public static synchronized TimerDAOInterface getInstance(){
        if (instance == null){
            instance = new TimerDAOFS();
        }
        return instance;
    }


    @Override
    public void insert(AbstractTimer timer){

    }

    @Override
    public void cancel(AbstractTimer timer){

    }

    @Override
    public void update(AbstractTimer timer){

    }

}
