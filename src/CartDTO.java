
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a CartDTO object, which is a data transfer object for a shopping cart.
 * It extends the BaseDTO class and provides additional functionality specific to a cart.
 */
public class CartDTO extends BaseDTO {

    // Fields and their corresponding data types
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("user_id", DTOTypes.INTEGER);
            put("status", DTOTypes.STRING);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    // Mapping of field order to field names
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "user_id");
            put(2, "status");
            put(3, "date_created");
            put(4, "date_modified");
        }
    };

    /**
     * Constructs a CartDTO object.
     * Initializes the fields and field order map using the superclass constructor.
     */
    public CartDTO() {
        super("cart", _fields, _fieldsOrderMap);
    }

    /**
     * Returns the home interface for CartDTO.
     * @return The home interface for CartDTO.
     */
    public IDTOHome getDtoHome() {
        return CartDTOHome.Instance();
    }
}