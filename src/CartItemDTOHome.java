
/**
 * This class represents a home for CartItemDTO objects.
 * It extends the BaseDTOHome class and provides methods for creating new instances of CartItemDTO.
 */
public class CartItemDTOHome extends BaseDTOHome {

    private static CartItemDTOHome instance = new CartItemDTOHome();

    private CartItemDTOHome() {
    }

    /**
     * Returns the singleton instance of CartItemDTOHome.
     *
     * @return the singleton instance of CartItemDTOHome
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of CartItemDTO.
     *
     * @return a new instance of CartItemDTO
     */
    public IDTO newDTOInstance() {
        return new CartItemDTO();
    }
}