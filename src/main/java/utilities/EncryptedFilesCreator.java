package utilities;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Created by Maor on 6/6/2017.
 */
public class EncryptedFilesCreator extends SimpleFileVisitor<Path> {
    private Path encryptionDirectoryParent;
    private ArrayList<Tuple<Path, Path>> filePathTuplesListToEncrypt = new ArrayList<>();

    public EncryptedFilesCreator(Path encryptionDirectoryParent) {
        this.encryptionDirectoryParent = encryptionDirectoryParent;
    }

    public ArrayList<Tuple<Path, Path>> getFilePathTuplesListToEncrypt() {
        return filePathTuplesListToEncrypt;
    }

    @Override
    public FileVisitResult visitFile(Path sourcePath, BasicFileAttributes basicFileAttributes) throws IOException {
        Path encryptedFileName = Paths.get(sourcePath.toString() + ".encrypted");
        Path pathToEncrypt = Paths.get(encryptionDirectoryParent.toString(), "encryption", encryptionDirectoryParent.relativize(encryptedFileName).toString());
        try {
            Files.createFile(pathToEncrypt);
        } catch (FileAlreadyExistsException e) {
            Files.delete(pathToEncrypt);
            Files.createFile(pathToEncrypt);
        }
        filePathTuplesListToEncrypt.add(new Tuple<>(sourcePath, pathToEncrypt));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path sourceDirectory, BasicFileAttributes basicFileAttributes) throws IOException {
        Path encryptedDirectory = Paths.get(encryptionDirectoryParent.toString(), "encryption", encryptionDirectoryParent.relativize(sourceDirectory).toString());
        try {
            Files.createDirectory(encryptedDirectory);
        } catch (FileAlreadyExistsException e) {
            FileUtils.forceDelete(encryptedDirectory.toFile());
            Files.createDirectory(encryptedDirectory);
        }

        return FileVisitResult.CONTINUE;
    }
}
