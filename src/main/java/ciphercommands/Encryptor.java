package ciphercommands;

import algorithms.Algorithm;
import algorithms.AlgorithmFactoryMenu;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.FileUtils;
import suppliers.KeySupplier;
import utilities.EncryptFiles;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Maor on 5/27/2017.
 */
public class Encryptor extends CipherCommand {
    @Inject
    public Encryptor(@Named("random key supplier") KeySupplier<Integer> keySupplier) {
        super(keySupplier);
    }

    @Override
    public void execute(Path path) throws IOException, ClassNotFoundException {
        AlgorithmFactoryMenu algorithmFactoryMenu = new AlgorithmFactoryMenu(keySupplier);
        Path pathParent = path.getParent();
        Path encryptionDirectory = Paths.get(pathParent.toString(), "encryption");
        try {
            Files.createDirectory(encryptionDirectory);
        } catch (FileAlreadyExistsException e) {
            FileUtils.forceDelete(encryptionDirectory.toFile());
            Files.createDirectory(encryptionDirectory);
        }
        Algorithm<?> algorithm = algorithmFactoryMenu.getElement().get();
        algorithm.supplyValidKeyToAlgorithm();
        algorithm.createKeyBinFile(pathParent);
        Files.walkFileTree(path, new EncryptFiles(pathParent, algorithm));
        System.out.println("Encryption Process ended successfully");
    }
}

