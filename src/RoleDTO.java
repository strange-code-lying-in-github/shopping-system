import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a Role Data Transfer Object (DTO).
 * This class extends the BaseDTO class and provides additional functionality for role-related data.
 */
public class RoleDTO extends BaseDTO {

    /**
     * A map that defines the fields of the RoleDTO and their corresponding data types.
     */
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("name", DTOTypes.STRING);
            put("description", DTOTypes.STRING);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    /**
     * A map that defines the order of the fields in the RoleDTO.
     */
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "name");
            put(2, "description");
            put(3, "date_created");
            put(4, "date_modified");
        }
    };

    /**
     * Constructs a new RoleDTO object.
     * Initializes the fields and fields order map using the super constructor from the BaseDTO class.
     */
    public RoleDTO() {
        super("role", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the DTO home for RoleDTO.
     * @return The instance of the RoleDTOHome class.
     */
    public IDTOHome getDtoHome() {
        return RoleDTOHome.Instance();
    }
}