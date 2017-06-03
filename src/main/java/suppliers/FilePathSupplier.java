package suppliers;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Created by Maor on 5/28/2017.
 */
public class FilePathSupplier {
    public Path supplyFilePath(){
        System.out.println("Enter File path");
        Scanner scanner = new Scanner(System.in);
        File file;
        while (!(file = new File(scanner.nextLine())).exists()){
            System.out.println("Enter File path");
        }
        return file.toPath();
    }
}
