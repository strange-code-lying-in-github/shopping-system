import java.util.List;

/**
 * This interface represents a product catalog.
 * It provides a method to retrieve a list of products.
 */
public interface IProductCatalog {
    /**
     * Retrieves a list of products.
     *
     * @return the list of products
     */
    List<IDTO> getProducts();
}