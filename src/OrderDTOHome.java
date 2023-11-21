
/**
 * This class represents a home for OrderDTO objects.
 * It extends the BaseDTOHome class and provides methods for creating new instances of OrderDTO.
 */
public class OrderDTOHome extends BaseDTOHome {

    private static OrderDTOHome instance = new OrderDTOHome();

    private OrderDTOHome() {
    }

    /**
     * Returns the singleton instance of OrderDTOHome.
     *
     * @return The singleton instance of OrderDTOHome.
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of OrderDTO.
     *
     * @return A new instance of OrderDTO.
     */
    public IDTO newDTOInstance() {
        return new OrderDTO();
    }
}