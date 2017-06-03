package suppliers;

import algorithms.Algorithm;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Maor on 5/25/2017.
 */
public class AlgorithmSupplier {
    HashMap<String, Algorithm> algorithmHashMap;

    public HashMap<String, Algorithm> getAlgorithmHashMap() {
        return algorithmHashMap;
    }

    AlgorithmSupplier algorithmSupplier;

    public Algorithm<?> getAlgorithm(String message) {
        System.out.println(message);
        showAlgorithms();
        Scanner scanner = new Scanner(System.in);
        String algorithm;
        while (!algorithmHashMap.containsKey(algorithm = scanner.nextLine())) {
            System.out.println("Algorithm is not exist");
            System.out.println(message);
            showAlgorithms();
        }
        return algorithmHashMap.get(algorithm);
    }

    public void showAlgorithms() {
        for (String algorithm : algorithmHashMap.keySet()) {
            System.out.println(algorithm);

        }
    }
}
