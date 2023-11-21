
/**
 * This class represents a home for OrderItemDTO objects.
 * It extends the BaseDTOHome class and provides methods for creating new instances of OrderItemDTO.
 */
public class OrderItemDTOHome extends BaseDTOHome {

    private static OrderItemDTOHome instance = new OrderItemDTOHome();

    private OrderItemDTOHome() {
    }

    /**
     * Returns the singleton instance of OrderItemDTOHome.
     *
     * @return the singleton instance of OrderItemDTOHome
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of OrderItemDTO.
     *
     * @return a new instance of OrderItemDTO
     */
    public IDTO newDTOInstance() {
        return new OrderItemDTO();
    }
}