package utilities;

import com.google.common.base.Stopwatch;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by Maor on 6/13/2017.
 */
public class EventNotifier {
    private Logger logger;

    public EventNotifier(Logger logger) {
        this.logger = logger;
    }

    public void notifyEventIsStarted(String eventName) {
        logger.info(eventName + " event is started...");
    }

    public void notifyEventIsEnded(String eventName, Stopwatch timer) {
        logger.info(eventName + " event is ended. duration - " + timer);
    }


}
