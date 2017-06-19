package ciphercommands;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import modules.CipherCommandModule;
import suppliers.KeySupplier;
import utilities.Menu;

import java.util.HashMap;

/**
 * Created by Maor on 5/28/2017.
 */
public class CipherCommandMenu extends Menu<CipherCommand> {
    @Override
    public void intializeMap() {
        Injector injector = Guice.createInjector(new CipherCommandModule());
        map.put("encryptor", new Encryptor(injector.getInstance(Key.get(new TypeLiteral<KeySupplier<Integer>>(){}, Names.named("random key supplier")))));
        map.put("decryptor", new Decryptor(injector.getInstance(Key.get(new TypeLiteral<KeySupplier<Integer>>(){}, Names.named("user key supplier")))));
    }

    public CipherCommandMenu(){
        super(new HashMap<>(), "Enter Cipher Command", "Cipher Command doesn't exist");
    }
}
