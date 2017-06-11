package utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maor on 6/7/2017.
 */
public abstract class Audible {
    protected List<Listner> listnerList;

    public Audible(List<Listner> listnerList){
        this.listnerList = listnerList;
    }

    public void registerListner(Listner listner){
        listnerList.add(listner);
    }

    public void removeListner(Listner listner){
        listnerList.remove(listner);
    }

    public void notifyAllEventStarted(String event){
        listnerList.forEach(listner -> listner.startEvent(event));
    }

    public void notifyAllEventEnded(String event){
        listnerList.forEach(listner -> listner.endEvent(event));
    }

}
