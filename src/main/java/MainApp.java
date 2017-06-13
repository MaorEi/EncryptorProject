import ciphercommands.CipherCommand;
import ciphercommands.CipherCommandMenu;
import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.CipherCommandModule;
import suppliers.PathSupplier;
import threads.ThreadMode;
import threads.ThreadModeExecutorFactoryMenu;
import utilities.EventListner;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * Created by Maor on 5/27/2017.
 */
public class MainApp {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new CipherCommandModule());
        CipherCommand cipherCommand = new CipherCommandMenu().getElement();
        PathSupplier filePathSupplier = new PathSupplier();
        //Encryptor encryptor = new Encryptor(new RandomKeySupplier());
        //cipherCommand.execute(Paths.get("C:/Users/Maor/Desktop/directoryToEncrypt"));
        ExecutorService executorService = new ThreadModeExecutorFactoryMenu().getElement().get();

        cipherCommand.execute(filePathSupplier.supplyPath(), executorService);
        /*Path pathExample = Paths.get("C:\\Users\\Maor\\Desktop\\encryption\\directoryToEncrypt\\hi.txt.encrypted");
        Files.createFile(pathExample);*/

    }
}
