
/**
 * This class represents a home for RoleDTO objects.
 * It extends the BaseDTOHome class and provides methods for creating new instances of RoleDTO.
 */
public class RoleDTOHome extends BaseDTOHome {

    private static RoleDTOHome instance = new RoleDTOHome();

    private RoleDTOHome() {
    }

    /**
     * Returns the singleton instance of RoleDTOHome.
     *
     * @return The singleton instance of RoleDTOHome.
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of RoleDTO.
     *
     * @return A new instance of RoleDTO.
     */
    public IDTO newDTOInstance() {
        return new RoleDTO();
    }
}