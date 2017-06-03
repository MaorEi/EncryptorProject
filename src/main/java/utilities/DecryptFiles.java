package utilities;

import algorithms.Algorithm;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DecryptFiles extends SimpleFileVisitor<Path> {
    private Path decryptionDirectoryParent;
    private Algorithm<?> algorithm;

    public DecryptFiles(Path decryptionDirectoryPath, Algorithm<?> algorithm) {
        this.decryptionDirectoryParent = decryptionDirectoryPath;
        this.algorithm = algorithm;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String originalFileFullName = FilenameUtils.removeExtension(file.toString());
        String originalFileExtension = FilenameUtils.getExtension(originalFileFullName);
        String originalFileName = FilenameUtils.removeExtension(originalFileFullName);
        Path decryptedFileName = Paths.get(originalFileName + "_decrypted." + originalFileExtension);
        Path pathToDecrypt = Paths.get(decryptionDirectoryParent.toString(), "decryption", decryptionDirectoryParent.relativize(decryptedFileName).toString());
        Path decryptedFile;
        try {
            decryptedFile = Files.createFile(pathToDecrypt);
        } catch (FileAlreadyExistsException e) {
            Files.delete(pathToDecrypt);
            decryptedFile = Files.createFile(pathToDecrypt);
        }
        algorithm.decrypt(Files.newInputStream(file), Files.newOutputStream(decryptedFile));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path decryptedDirectory = Paths.get(decryptionDirectoryParent.toString(), "decryption", decryptionDirectoryParent.relativize(dir).toString());
        try {
            Files.createDirectory(decryptedDirectory);
        } catch (FileAlreadyExistsException e) {
            FileUtils.forceDelete(decryptedDirectory.toFile());
            Files.createDirectory(decryptedDirectory);
        }
        return FileVisitResult.CONTINUE;
    }

}
