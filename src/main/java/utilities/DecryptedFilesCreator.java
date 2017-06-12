package utilities;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Created by Maor on 6/11/2017.
 */
public class DecryptedFilesCreator extends SimpleFileVisitor<Path> {
    private Path decryptionDirectoryParent;
    private ArrayList<Tuple<Path, Path>> filePathTuplesListToEncrypt = new ArrayList<>();

    public DecryptedFilesCreator(Path decryptionDirectoryParent) {
        this.decryptionDirectoryParent = decryptionDirectoryParent;
    }

    public ArrayList<Tuple<Path, Path>> getFilePathTuplesListToDecrypt() {
        return filePathTuplesListToEncrypt;
    }

    @Override
    public FileVisitResult visitFile(Path sourcePath, BasicFileAttributes basicFileAttributes) throws IOException {
        String originalFileFullName = FilenameUtils.removeExtension(sourcePath.toString());
        String originalFileExtension = FilenameUtils.getExtension(originalFileFullName);
        String originalFileName = FilenameUtils.removeExtension(originalFileFullName);
        Path decryptedFileName = Paths.get(originalFileName + "_decrypted." + originalFileExtension);
        Path pathToDecrypt = Paths.get(decryptionDirectoryParent.toString(), "decryption", decryptionDirectoryParent.relativize(decryptedFileName).toString());
        try {
            Files.createFile(pathToDecrypt);
        } catch (FileAlreadyExistsException e) {
            Files.delete(pathToDecrypt);
            Files.createFile(pathToDecrypt);
        }
        filePathTuplesListToEncrypt.add(new Tuple<>(sourcePath, pathToDecrypt));
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
