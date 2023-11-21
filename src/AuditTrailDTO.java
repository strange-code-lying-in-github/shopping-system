import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a Data Transfer Object (DTO) for the Audit Trail entity.
 * It extends the BaseDTO class and provides a mapping of fields and their types.
 * The AuditTrailDTO class also provides a method to retrieve the DTO home instance.
 */
public class AuditTrailDTO extends BaseDTO {

    // Field mapping for the Audit Trail entity
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("user_id", DTOTypes.INTEGER);
            put("action", DTOTypes.STRING);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    // Field order mapping for the Audit Trail entity
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "user_id");
            put(2, "action");
            put(3, "date_created");
            put(4, "date_modified");
        }
    };

    /**
     * Constructs a new instance of the AuditTrailDTO class.
     * Initializes the field and field order mappings by calling the super constructor of BaseDTO.
     */
    public AuditTrailDTO() {
        super("audittrail", _fields, _fieldsOrderMap);
    }

    /**
     * Returns the DTO home instance for the AuditTrailDTO class.
     *
     * @return The DTO home instance.
     */
    public IDTOHome getDtoHome() {
        return AuditTrailDTOHome.Instance();
    }
}