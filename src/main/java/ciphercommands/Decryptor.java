package ciphercommands;

import algorithms.Algorithm;
import algorithms.AlgorithmFactoryMenu;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.FileUtils;
import suppliers.KeySupplier;
import utilities.DecryptFiles;
import utilities.EncryptFiles;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Decryptor extends CipherCommand {

    @Inject
    public Decryptor(@Named("user key supplier") KeySupplier<Integer> keySupplier) {
        super(keySupplier);
    }

    public void execute(Path path) throws IOException, ClassNotFoundException {
        AlgorithmFactoryMenu algorithmFactoryMenu = new AlgorithmFactoryMenu(keySupplier);
        Path pathParent = path.getParent();
        Path decryptionDirectory = Paths.get(pathParent.toString(), "decryption");

        try {
            Files.createDirectory(Paths.get(path.getParent().toString(), "decryption"));
        } catch (FileAlreadyExistsException e) {
            FileUtils.forceDelete(decryptionDirectory.toFile());
            Files.createDirectory(decryptionDirectory);
        }
        Algorithm<?> algorithm = algorithmFactoryMenu.getElement().get();
        algorithm.supplyValidKeyToAlgorithm();
        Files.walkFileTree(path, new DecryptFiles(path.getParent(), algorithm));
        System.out.println("Decryption Process ended successfully");
    }
}
