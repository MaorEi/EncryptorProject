package ciphercommands;

import algorithms.Algorithm;
import algorithms.AlgorithmFactoryMenu;
import com.google.common.base.Stopwatch;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import suppliers.KeySupplier;
import threads.ThreadMode;
import utilities.EncryptedFilesCreator;
import utilities.EventNotifier;
import utilities.Tuple;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * Created by Maor on 5/27/2017.
 */
public class Encryptor extends CipherCommand {

    @Inject
    public Encryptor(@Named("random key supplier") KeySupplier<Integer> keySupplier) {
        super(keySupplier);
    }

    @Override
    public void execute(Path path, ExecutorService executorService) throws Exception {
        PropertyConfigurator.configure("log4j.properties");
        eventNotifier.notifyEventIsStarted("Encryptor");
        AlgorithmFactoryMenu algorithmFactoryMenu = new AlgorithmFactoryMenu(keySupplier);

        Path pathParent = path.getParent();
        Path encryptionDirectory = Paths.get(pathParent.toString(), "encryption");
        try {
            Files.createDirectory(encryptionDirectory);
        } catch (FileAlreadyExistsException e) {
            FileUtils.forceDelete(encryptionDirectory.toFile());
            Files.createDirectory(encryptionDirectory);
        }

        final Algorithm<?> algorithm = (Algorithm<?>) doEvent(() -> algorithmFactoryMenu.getElement().get(), "Algorithm choosing");

        doEvent(() -> {
            algorithm.supplyValidKeyToAlgorithm();
            return null;
        }, "Key supplying");

        doEvent(() -> {
            algorithm.createKeyBinFile(pathParent);
            return null;
        }, "Key bin file creation");


        Consumer<Tuple<Path, Path>> consumerTask = (tuplePath) -> {
            try {
                doEvent(() -> {
                    try {
                        algorithm.encrypt(Files.newInputStream(tuplePath.getFirst()), Files.newOutputStream(tuplePath.getSecond()));
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                    return null;
                }, Thread.currentThread().getName() + " - Encryption of " + tuplePath.getFirst().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        EncryptedFilesCreator encryptedFilesCreator = new EncryptedFilesCreator(pathParent);
        doEvent(() -> {
            Files.walkFileTree(path, encryptedFilesCreator);
            return null;
        }, "Encryption Tree creation");

        ThreadMode threadMode = new ThreadMode(consumerTask, encryptedFilesCreator.getFilePathTuplesListToEncrypt(), executorService);
        doEvent(() -> {
            threadMode.activate("Encryption");
            return null;
        }, "Encryption");
    }

}

