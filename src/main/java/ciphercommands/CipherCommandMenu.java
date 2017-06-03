package ciphercommands;

import suppliers.FileKeySupplier;
import suppliers.FilePathSupplier;
import suppliers.RandomKeySupplier;
import suppliers.UserKeySupplier;
import utilities.Menu;

import java.util.HashMap;

/**
 * Created by Maor on 5/28/2017.
 */
public class CipherCommandMenu extends Menu<CipherCommand> {
    @Override
    public void intializeMap() {
        map.put("encryptor", new Encryptor(new RandomKeySupplier()));
        map.put("decryptor", new Decryptor(new FileKeySupplier<>()));
    }

    public CipherCommandMenu(){
        super(new HashMap<>(), "enter cipher command", "cipher cammand isn't exist");
    }
}
