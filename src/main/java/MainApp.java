import ciphercommands.CipherCommand;
import ciphercommands.CipherCommandMenu;
import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.CipherCommandModule;
import suppliers.PathSupplier;

import java.io.IOException;

/**
 * Created by Maor on 5/27/2017.
 */
public class MainApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new CipherCommandModule());
        CipherCommand cipherCommand = new CipherCommandMenu().getElement();
       PathSupplier filePathSupplier = new PathSupplier();
        //Encryptor encryptor = new Encryptor(new RandomKeySupplier());
        //cipherCommand.execute(Paths.get("C:/Users/Maor/Desktop/directoryToEncrypt"));
        cipherCommand.execute(filePathSupplier.supplyPath());
        /*Path pathExample = Paths.get("C:\\Users\\Maor\\Desktop\\encryption\\directoryToEncrypt\\hi.txt.encrypted");
        Files.createFile(pathExample);*/

    }
}
