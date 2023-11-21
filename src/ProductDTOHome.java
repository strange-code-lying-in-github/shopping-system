
/**
 * This class represents a home for ProductDTO objects.
 * It extends the BaseDTOHome class and implements the IDTOHome interface.
 */
public class ProductDTOHome extends BaseDTOHome {

    private static ProductDTOHome instance = new ProductDTOHome();

    private ProductDTOHome() {
    }

    /**
     * Returns the singleton instance of ProductDTOHome.
     *
     * @return the singleton instance of ProductDTOHome
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of ProductDTO.
     *
     * @return a new instance of ProductDTO
     */
    public IDTO newDTOInstance() {
        return new ProductDTO();
    }
}