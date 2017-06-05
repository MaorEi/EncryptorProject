package suppliers;

import validators.ConcretePathValidator;
import validators.PathValidator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Maor on 5/28/2017.
 */
public class PathSupplier {
    private PathValidator pathValidator;
    private String headMessage;

    public PathSupplier(PathValidator pathValidator, String headMessage) {
        this.pathValidator = pathValidator;
        this.headMessage = headMessage;
    }

    public PathSupplier() {
        this.pathValidator  = new ConcretePathValidator();
        this.headMessage= "Enter File Path:";
    }

    public Path supplyPath() {
        System.out.println(headMessage);
        Scanner scanner = new Scanner(System.in);
        Path path;
        while (!pathValidator.isValidPath(path = Paths.get(scanner.nextLine()))) {
            System.out.println("File Path doesn't exists");
            System.out.println(headMessage);
        }
        return path;
    }
}
