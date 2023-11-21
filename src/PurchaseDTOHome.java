
/**
 * The PurchaseDTOHome class represents a home for PurchaseDTO objects.
 * It extends the BaseDTOHome class and implements the IDTOHome interface.
 * This class provides methods for creating new instances of PurchaseDTO objects.
 */
public class PurchaseDTOHome extends BaseDTOHome {

    private static PurchaseDTOHome instance = new PurchaseDTOHome();

    private PurchaseDTOHome() {
    }

    /**
     * Returns the singleton instance of PurchaseDTOHome.
     *
     * @return the singleton instance of PurchaseDTOHome
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of PurchaseDTO.
     *
     * @return a new instance of PurchaseDTO
     */
    public IDTO newDTOInstance() {
        return new PurchaseDTO();
    }
}