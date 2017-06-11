package utilities;

import com.google.common.base.Stopwatch;

/**
 * Created by Maor on 6/7/2017.
 */
public class EventListner implements Listner {
    public void startEvent(String event) {
        System.out.println(event + " Event starts now");
    }

    @Override
    public void endEvent(String event) {
        System.out.println(event + " Event ended in ");
    }
}
