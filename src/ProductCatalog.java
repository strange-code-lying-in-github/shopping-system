import java.util.ArrayList;
import java.util.List;

/**
 * The ProductCatalog class represents a catalog of products.
 * It implements the IProductCatalog interface.
 */
public class ProductCatalog implements IProductCatalog {
    private List<IDTO> products = new ArrayList<IDTO>();

    /**
     * Constructs a ProductCatalog object with the specified list of products.
     * 
     * @param products the list of products to be added to the catalog
     */
    public ProductCatalog(List<IDTO> products) {
        this.products = products;
    }

    /**
     * Returns the list of products in the catalog.
     * 
     * @return the list of products
     */
    public List<IDTO> getProducts() {
        return products;
    }
}