package utilities;

import algorithms.Algorithm;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * Created by Maor on 5/27/2017.
 */
public class EncryptFiles extends SimpleFileVisitor<Path> {
    private Path encryptionDirectoryParent;
    private Algorithm<?> algorithm;

    public EncryptFiles(Path encryptionDirectoryParent, Algorithm<?> algorithm) {
        this.encryptionDirectoryParent = encryptionDirectoryParent;
        this.algorithm = algorithm;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path encryptedFileName = Paths.get(file.toString() + ".encrypted");
        Path pathToEncrypt = Paths.get(encryptionDirectoryParent.toString(), "encryption", encryptionDirectoryParent.relativize(encryptedFileName).toString());
        Path encryptedFile;
        try {
            encryptedFile = Files.createFile(pathToEncrypt);
        } catch (FileAlreadyExistsException e) {
            Files.delete(pathToEncrypt);
            encryptedFile = Files.createFile(pathToEncrypt);
        }
        algorithm.encrypt(Files.newInputStream(file), Files.newOutputStream(encryptedFile));

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path encryptedDirectory = Paths.get(encryptionDirectoryParent.toString(), "encryption", encryptionDirectoryParent.relativize(dir).toString());
        try {
            Files.createDirectory(encryptedDirectory);
        } catch (FileAlreadyExistsException e) {
            FileUtils.forceDelete(encryptedDirectory.toFile());
            Files.createDirectory(encryptedDirectory);
        }

        return FileVisitResult.CONTINUE;
    }

}
