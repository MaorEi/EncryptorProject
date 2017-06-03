package suppliers;

import java.util.Scanner;

/**
 * Created by Maor on 5/23/2017.
 */
public class UserKeySupplier implements KeySupplier<Integer> {
    public Integer supplyKey() {
        System.out.println("enter key:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
