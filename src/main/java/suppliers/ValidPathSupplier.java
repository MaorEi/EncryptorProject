package suppliers;

import validators.PathValidator;

/**
 * Created by Maor on 6/4/2017.
 */
public class ValidPathSupplier {
    private PathSupplier filePathSupplier;
    private PathValidator pathValidator;

    public ValidPathSupplier(PathSupplier filePathSupplier, PathValidator pathValidator) {
        this.filePathSupplier = filePathSupplier;
        this.pathValidator = pathValidator;
    }
}
