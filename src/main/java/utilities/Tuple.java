package utilities;

/**
 * Created by Maor on 5/23/2017.
 */
public class Tuple<T,K> {
    private T first;
    private K second;

    public Tuple(T first, K second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(K second) {
        this.second = second;
    }

    public String toString(){
        return "first valeu: " + first.toString() + System.getProperty("line.separator") + "second value:" + second.toString() + "\n";
    }
}
