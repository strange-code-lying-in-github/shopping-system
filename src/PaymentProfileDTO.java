
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a payment profile data transfer object.
 * This class extends the BaseDTO class and provides specific fields and field order for the payment profile.
 */
public class PaymentProfileDTO extends BaseDTO {

    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("name", DTOTypes.STRING);
            put("description", DTOTypes.STRING);
            put("type", DTOTypes.STRING);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "name");
            put(2, "description");
            put(3, "type");
            put(4, "status");
            put(5, "date_created");
            put(6, "date_modified");
        }
    };

    /**
     * Constructs a new PaymentProfileDTO object.
     * Initializes the fields and field order map for the payment profile.
     */
    public PaymentProfileDTO() {
        super("paymentprofile", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the DTO home for the payment profile.
     * @return The DTO home for the payment profile.
     */
    public IDTOHome getDtoHome() {
        return PaymentProfileDTOHome.Instance();
    }
}