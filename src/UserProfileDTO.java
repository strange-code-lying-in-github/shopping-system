
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a Data Transfer Object (DTO) for a user profile.
 * It extends the BaseDTO class and provides mappings for the fields and their types.
 */
public class UserProfileDTO extends BaseDTO {

    // Mapping of field names to their corresponding types
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("user_id", DTOTypes.INTEGER);
            put("paymentprofile_id", DTOTypes.INTEGER);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    // Mapping of field order to their corresponding names
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "user_id");
            put(2, "paymentprofile_id");
            put(3, "date_created");
            put(4, "date_modified");
        }
    };

    /**
     * Constructs a new UserProfileDTO object.
     * Initializes the superclass with the entity name, field mappings, and field order mappings.
     */
    public UserProfileDTO() {
        super("userprofile", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the home interface for the UserProfileDTO.
     * @return The home interface for the UserProfileDTO.
     */
    public IDTOHome getDtoHome() {
        return UserProfileDTOHome.Instance();
    }
}