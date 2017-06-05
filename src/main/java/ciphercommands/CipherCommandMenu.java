package ciphercommands;

import suppliers.KeyPathSupplier;
import suppliers.RandomKeySupplier;
import utilities.Menu;

import java.util.HashMap;

/**
 * Created by Maor on 5/28/2017.
 */
public class CipherCommandMenu extends Menu<CipherCommand> {
    @Override
    public void intializeMap() {
        map.put("encryptor", new Encryptor(new RandomKeySupplier()));
        map.put("decryptor", new Decryptor(new KeyPathSupplier<>()));
    }

    public CipherCommandMenu(){
        super(new HashMap<>(), "Enter Cipher Command", "Cipher Command doesn't exist");
    }
}
