import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a Data Transfer Object (DTO) for User Roles.
 * It extends the BaseDTO class and provides specific fields and methods for User Roles.
 */
public class UserRoleDTO extends BaseDTO {

    // Fields map for User Role DTO
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("user_id", DTOTypes.INTEGER);
            put("role_id", DTOTypes.INTEGER);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    // Fields order map for User Role DTO
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "user_id");
            put(2, "role_id");
            put(4, "date_created");
            put(5, "date_modified");
        }
    };

    /**
     * Constructs a new UserRoleDTO object.
     * Initializes the DTO name, fields map, and fields order map.
     */
    public UserRoleDTO() {
        super("userrole", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the DTO home for User Roles.
     * @return The IDTOHome instance for User Roles.
     */
    public IDTOHome getDtoHome() {
        return UserRoleDTOHome.Instance();
    }
}