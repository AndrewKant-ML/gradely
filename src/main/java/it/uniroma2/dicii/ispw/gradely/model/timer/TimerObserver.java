package it.uniroma2.dicii.ispw.gradely.model.timer;

import java.util.List;

public interface TimerObserver {
    void timeIsUp(AbstractTimer timer);
}
