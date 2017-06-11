package threads;

import algorithms.Algorithm;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import utilities.EncryptedFilesCreator;
import utilities.EventListner;
import utilities.Listner;
import utilities.Tuple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Maor on 6/8/2017.
 */
public class ThreadModeTest {
    private Algorithm<?> algorithm = mock(Algorithm.class);
    private List<Tuple<Path, Path>> tuplePathList = new ArrayList<>();
    private EncryptedFilesCreator encryptedFilesCreator;
    private Listner listner = new EventListner();
    private List<Listner> listnerList = new ArrayList<>();

    @Test
    public void testActivateASync() throws Exception {
        listnerList.add(listner);
        Path directory = Files.createTempDirectory("root");
        Path directoryEncrypted = Files.createTempDirectory(directory, "encryption");
        Path currentPath;
        Path currentEncryptedPath;

        for (int i = 0; i < 100; i++) {
            currentPath = Files.createTempFile(directory, String.valueOf(i), ".txt");
            currentEncryptedPath = Files.createTempFile(directoryEncrypted, currentPath.getFileName().toString(), "encrypted");
            OutputStream outputStream = Files.newOutputStream(currentPath);
            for (int j = 0; j < 10000000; j++) {
                outputStream.write(1);
            }
            tuplePathList.add(new Tuple<>(currentPath, currentEncryptedPath));
        }

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) throws IOException {
                InputStream inputStream = (InputStream) invocation.getArguments()[0];
                OutputStream outputStream = (OutputStream) invocation.getArguments()[1];
                IOUtils.copy(inputStream, outputStream);
                return null;
            }
        }).when(algorithm).encrypt(any(InputStream.class), any(OutputStream.class));

        ThreadMode threadMode = new ThreadMode(tuple -> {
            try {
                algorithm.encrypt(Files.newInputStream(tuple.getFirst()), Files.newOutputStream(tuple.getSecond()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, tuplePathList, Executors.newFixedThreadPool(3), listnerList);
        threadMode.activate("Encryption Test");
    }
}