
/**
 * This class represents a home for CartDTO objects.
 * It extends the BaseDTOHome class and implements the IDTOHome interface.
 * It provides methods for creating new instances of CartDTO objects.
 */
public class CartDTOHome extends BaseDTOHome {

    private static CartDTOHome instance = new CartDTOHome();

    private CartDTOHome() {
    }

    /**
     * Returns the singleton instance of CartDTOHome.
     *
     * @return the singleton instance of CartDTOHome
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of CartDTO.
     *
     * @return a new instance of CartDTO
     */
    public IDTO newDTOInstance() {
        return new CartDTO();
    }
}