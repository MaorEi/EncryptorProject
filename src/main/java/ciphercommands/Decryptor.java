package ciphercommands;

import algorithms.Algorithm;
import algorithms.AlgorithmFactoryMenu;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.FileUtils;
import suppliers.KeySupplier;
import threads.AlgorithmConsumerTask;
import threads.ThreadMode;
import utilities.DecryptFiles;
import utilities.DecryptedFilesCreator;
import utilities.EncryptFiles;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;

public class Decryptor extends CipherCommand {

    @Inject
    public Decryptor(@Named("user key supplier") KeySupplier<Integer> keySupplier) {
        super(keySupplier);
    }

    public void execute(Path path, ExecutorService executorService) throws Exception {
        notifyAllEventStarted("Decryptor");
        AlgorithmFactoryMenu algorithmFactoryMenu = new AlgorithmFactoryMenu(keySupplier);
        Path pathParent = path.getParent();
        Path decryptionDirectory = Paths.get(pathParent.toString(), "decryption");

        try {
            Files.createDirectory(Paths.get(path.getParent().toString(), "decryption"));
        } catch (FileAlreadyExistsException e) {
            FileUtils.forceDelete(decryptionDirectory.toFile());
            Files.createDirectory(decryptionDirectory);
        }

        final Algorithm<?> algorithm = (Algorithm<?>) doEvent(() -> algorithmFactoryMenu.getElement().get(), "Algorithm choosing");

        doEvent(() -> {
            algorithm.supplyValidKeyToAlgorithm();
            return null;
        }, "Key supplying");

        AlgorithmConsumerTask algorithmConsumerTask = new AlgorithmConsumerTask((tuplePath) -> {
            try {
                algorithm.decrypt(Files.newInputStream(tuplePath.getFirst()), Files.newOutputStream(tuplePath.getSecond()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        DecryptedFilesCreator decryptedFilesCreator = new DecryptedFilesCreator(path.getParent());

        doEvent(() -> {
            Files.walkFileTree(path, decryptedFilesCreator);
            return null;
        }, "Decryption Tree creation");

        ThreadMode threadMode = new ThreadMode(algorithmConsumerTask, decryptedFilesCreator.getFilePathTuplesListToEncrypt(), executorService, listnerList);
        threadMode.activate("Decryption");
        notifyAllEventEnded("Decryptor");
    }
}
