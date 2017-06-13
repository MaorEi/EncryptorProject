package ciphercommands;

import algorithms.Algorithm;
import algorithms.AlgorithmFactoryMenu;
import com.google.common.base.Stopwatch;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import suppliers.KeyPathSupplier;
import suppliers.KeySupplier;
import threads.AlgorithmConsumerTask;
import threads.ThreadMode;
import utilities.*;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class Decryptor extends CipherCommand {
    private static final Logger logger = Logger.getLogger(CipherCommand.class);

    @Inject
    public Decryptor(@Named("user key supplier") KeySupplier<Integer> keySupplier) {
        super(keySupplier);
    }

    public void execute(Path path, ExecutorService executorService) throws Exception {
        PropertyConfigurator.configure("log4j.properties");
        eventNotifier.notifyEventIsStarted("Decryptor");
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
            algorithm.supplyValidKeyBinValueToAlgorithm();
            return null;
        }, "key.bin file supplying");

        Consumer<Tuple<Path, Path>> consumerTask = (tuplePath) -> {
            try {
                doEvent(() -> {
                    try {
                        algorithm.decrypt(Files.newInputStream(tuplePath.getFirst()), Files.newOutputStream(tuplePath.getSecond()));
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                    return null;
                }, Thread.currentThread().getName() + " - Decryption of " + tuplePath.getFirst().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        DecryptedFilesCreator decryptedFilesCreator = new DecryptedFilesCreator(path.getParent());
        doEvent(() -> {
            Files.walkFileTree(path, decryptedFilesCreator);
            return null;
        }, "Decryption Tree creation");

        ThreadMode threadMode = new ThreadMode(consumerTask, decryptedFilesCreator.getFilePathTuplesListToDecrypt(), executorService);
        doEvent(() -> {
            threadMode.activate("Decryption");
            return null;
        }, "Decryption");
    }
}
