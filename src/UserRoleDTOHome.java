
/**
 * This class represents a home for UserRoleDTO objects.
 * It extends the BaseDTOHome class and provides methods for creating new instances of UserRoleDTO.
 */
public class UserRoleDTOHome extends BaseDTOHome {

    private static UserRoleDTOHome instance = new UserRoleDTOHome();

    private UserRoleDTOHome() {
    }

    /**
     * Returns the singleton instance of UserRoleDTOHome.
     *
     * @return The singleton instance of UserRoleDTOHome.
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of UserRoleDTO.
     *
     * @return A new instance of UserRoleDTO.
     */
    public IDTO newDTOInstance() {
        return new UserRoleDTO();
    }
}