package modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import suppliers.KeySupplier;
import suppliers.RandomKeySupplier;
import suppliers.UserKeySupplier;

/**
 * Created by Maor on 5/23/2017.
 */
public class CipherCommandModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<KeySupplier<Integer>>(){}).annotatedWith(Names.named("random key supplier")).to(RandomKeySupplier.class);
        bind(new TypeLiteral<KeySupplier<Integer>>(){}).annotatedWith(Names.named("user key supplier")).to(UserKeySupplier.class);
    }
}
