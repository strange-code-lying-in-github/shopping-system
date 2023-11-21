
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a data transfer object for an order item.
 * Extends the BaseDTO class.
 */
public class OrderItemDTO extends BaseDTO {

    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("cartitem_id", DTOTypes.INTEGER);
            put("order_id", DTOTypes.INTEGER);
            put("price", DTOTypes.FLOAT);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "cartitem_id");
            put(2, "order_id");
            put(3, "price");
            put(4, "date_created");
            put(5, "date_modified");
        }
    };

    /**
     * Constructs a new OrderItemDTO object.
     * Initializes the fields and fields order map.
     */
    public OrderItemDTO() {
        super("orderitem", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the DTO home for OrderItemDTO.
     * @return The DTO home for OrderItemDTO.
     */
    public IDTOHome getDtoHome() {
        return OrderItemDTOHome.Instance();
    }
}