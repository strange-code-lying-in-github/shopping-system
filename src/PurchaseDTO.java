
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a PurchaseDTO object that extends the BaseDTO class.
 * It contains fields and methods related to a purchase in the shopping system.
 */
public class PurchaseDTO extends BaseDTO {

    // Fields and their corresponding data types
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("user_id", DTOTypes.INTEGER);
            put("paymentprofile_id", DTOTypes.INTEGER);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    // Fields order map
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "user_id");
            put(2, "paymentprofile_id");
            put(4, "date_created");
            put(5, "date_modified");
        }
    };

    /**
     * Constructs a new PurchaseDTO object with the specified fields and fields order map.
     */
    public PurchaseDTO() {
        super("purchase", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the DTO home for PurchaseDTO.
     *
     * @return The IDTOHome instance for PurchaseDTO.
     */
    public IDTOHome getDtoHome() {
        return PurchaseDTOHome.Instance();
    }
}