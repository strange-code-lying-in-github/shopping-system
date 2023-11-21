/**
 * This class represents a home for UserDTO objects.
 * It extends the BaseDTOHome class and implements the IDTOHome interface.
 */
public class UserDTOHome extends BaseDTOHome {

    private static UserDTOHome instance = new UserDTOHome();

    private UserDTOHome() {
    }

    /**
     * Returns the singleton instance of UserDTOHome.
     *
     * @return the singleton instance of UserDTOHome
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of UserDTO.
     *
     * @return a new instance of UserDTO
     */
    public IDTO newDTOInstance() {
        return new UserDTO();
    }
}