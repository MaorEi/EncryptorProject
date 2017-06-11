package utilities;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Maor on 6/7/2017.
 */
public class EventListnerTest {
    EventListner eventListner = new EventListner();
    @Test
    public void simulateEventListner() throws InterruptedException {
        eventListner.startEvent("Encryption");
        TimeUnit.SECONDS.sleep(3);
        eventListner.endEvent("Encryption");
    }
}