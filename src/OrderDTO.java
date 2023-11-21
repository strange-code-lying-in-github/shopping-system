
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a Data Transfer Object (DTO) for an order.
 * Inherits from the BaseDTO class.
 */
public class OrderDTO extends BaseDTO {

    /**
     * A map that defines the fields of the OrderDTO and their corresponding data types.
     */
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("purchase_id", DTOTypes.INTEGER);
            put("cart_id", DTOTypes.INTEGER);
            put("user_id", DTOTypes.INTEGER);
            put("status", DTOTypes.STRING);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    /**
     * A map that defines the order of the fields in the OrderDTO.
     */
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "purchase_id");
            put(2, "cart_id");
            put(3, "user_id");
            put(4, "status");
            put(5, "date_created");
            put(6, "date_modified");
        }
    };

    /**
     * Constructs an instance of the OrderDTO class.
     * Initializes the fields and fields order map using the super constructor.
     */
    public OrderDTO() {
        super("order", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the DTO home for the OrderDTO.
     * @return The DTO home for the OrderDTO.
     */
    public IDTOHome getDtoHome() {
        return OrderDTOHome.Instance();
    }
}