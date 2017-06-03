package utilities;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by Maor on 5/28/2017.
 */
public abstract class Menu<T> {
    protected Map<String, T> map;
    protected String headerMessage;
    private String invalidMessage;

    public Menu(Map<String, T> map, String headerMessage, String invalidMessage) {
        this.map = map;
        this.headerMessage = headerMessage;
        this.invalidMessage = invalidMessage;
        intializeMap();
    }

    public abstract void intializeMap();

    private void showOptions() {
        System.out.println(headerMessage);
        for (String elemnt : map.keySet()) {
            System.out.println("- " + elemnt);
        }
    }

    public T getElement(){
        showOptions();
        Scanner scanner = new Scanner(System.in);
        String key;
        while (!map.containsKey(key = scanner.nextLine())){
            System.out.println(invalidMessage);
            showOptions();
        }
        return map.get(key);
    }

    public void setHeaderMessage(String headerMessage) {
        this.headerMessage = headerMessage;
    }

    public void setInvalidMessage(String invalidMessage) {
        this.invalidMessage = invalidMessage;
    }
}
