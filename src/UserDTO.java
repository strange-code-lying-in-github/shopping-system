
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a Data Transfer Object (DTO) for a user.
 * It extends the BaseDTO class and provides specific fields and field order mappings for a user.
 */
public class UserDTO extends BaseDTO {

    // Field mappings for user DTO
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("first_name", DTOTypes.STRING);
            put("last_name", DTOTypes.STRING);
            put("email_address", DTOTypes.STRING);
            put("password_hash", DTOTypes.STRING);
            put("active", DTOTypes.STRING);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    // Field order mappings for user DTO
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "first_name");
            put(2, "last_name");
            put(3, "email_address");
            put(4, "password_hash");
            put(5, "active");
            put(6, "date_created");
            put(7, "date_modified");
        }
    };

    /**
     * Constructs a new UserDTO object.
     * Initializes the DTO name, field mappings, and field order mappings.
     */
    public UserDTO() {
        super("user", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the home interface for UserDTO.
     * @return The instance of UserDTOHome.
     */
    public IDTOHome getDtoHome() {
        return UserDTOHome.Instance();
    }
}