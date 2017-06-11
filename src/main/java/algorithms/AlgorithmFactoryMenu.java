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

    private static String defaultHeaderMessage = "Enter Algorithm:";

    private KeySupplier<Integer> keySupplier;

    public AlgorithmFactoryMenu(KeySupplier<Integer> keySupplier) {
        super(new HashMap<>(), defaultHeaderMessage, "Algorithm is invalid.");
        this.keySupplier = keySupplier;
    }

    @Override
    public void intializeMap() {
        map.put("caesar", () -> {
            setHeaderMessage(defaultHeaderMessage);
            return new CaesarAlgorithm(keySupplier);
        });
        map.put("xor", () -> {
            setHeaderMessage(defaultHeaderMessage);
            return new XorAlgorithm(keySupplier);
        });
        map.put("mwo", () -> {
            setHeaderMessage(defaultHeaderMessage);
            return new MwoAlgorithm(keySupplier);
        });
        map.put("reverse", () -> {
            setHeaderMessage("Enter algorithm to be reversed:");
            Algorithm<?> algorithm = getElement().get();
            return new ReverseAlgorithm<>(algorithm);
        });
        map.put("repeat", () -> {
            setHeaderMessage("Enter Algorithm to be repeated:");
            Algorithm<?> algorithm = getElement().get();
            return new RepeatAlgorithm<>(algorithm);
        });
        map.put("double", () -> {
            setHeaderMessage("Enter first algorithm:");
            Algorithm<?> firstAlgorithm = getElement().get();
            setHeaderMessage("Enter second algorithm:");
            Algorithm<?> secondAlgorithm = getElement().get();
            return new DoubleAlgorithm<>(firstAlgorithm, secondAlgorithm);
        });

    }
}
