package algorithms;

import suppliers.KeySupplier;
import utilities.Menu;
import utilities.Tuple;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Created by Maor on 5/28/2017.
 */
public class AlgorithmFactoryMenu extends Menu<Supplier<Algorithm<?>>> {

    private KeySupplier<Integer> keySupplier;

    public AlgorithmFactoryMenu(KeySupplier<Integer> keySupplier) {
        super(new HashMap<>(), "Enter Algorithm", "Algorithm is invalid");
        this.keySupplier = keySupplier;
    }

    @Override
    public void intializeMap() {
        map.put("caesar", () -> new CaesarAlgorithm(keySupplier));
        map.put("xor", () -> new XorAlgorithm(keySupplier));
        map.put("mwo", () -> new MwoAlgorithm(keySupplier));
        map.put("reverse", () -> {
            setHeaderMessage("Enter algorithm to be reversed");
            Algorithm<?> algorithm = getElement().get();
            setHeaderMessage("Enter Algorithm");
            return new ReverseAlgorithm<>(algorithm);
        });
        map.put("repeat", () -> {
            Algorithm<?> algorithm = getElement().get();
            System.out.println("Enter number of repeats:");
            return new RepeatAlgorithm<>(algorithm, new Scanner(System.in).nextInt());
        });
        map.put("double", () -> {
            setHeaderMessage("Enter first algorithm");
            Algorithm<?> firstAlgorithm = getElement().get();
            setHeaderMessage("Enter second algorithm");
            Algorithm<?> secondAlgorithm = getElement().get();
            setHeaderMessage("Enter Algorithm");
            return new DoubleAlgorithm<>(firstAlgorithm, secondAlgorithm);
        });

    }
}
