
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a data transfer object for a cart item.
 * Extends the BaseDTO class.
 */
public class CartItemDTO extends BaseDTO {

    // Fields and their corresponding data types
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("cart_id", DTOTypes.INTEGER);
            put("product_id", DTOTypes.INTEGER);
            put("quantity", DTOTypes.INTEGER);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    // Mapping of field order to field names
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "cart_id");
            put(2, "product_id");
            put(3, "quantity");
            put(4, "date_created");
            put(5, "date_modified");
        }
    };

    /**
     * Constructs a CartItemDTO object.
     * Initializes the fields and field order map.
     */
    public CartItemDTO() {
        super("cartitem", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the DTO home for CartItemDTO.
     * @return The instance of CartItemDTOHome.
     */
    public IDTOHome getDtoHome() {
        return CartItemDTOHome.Instance();
    }
}