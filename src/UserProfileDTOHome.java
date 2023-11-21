
/**
 * This class represents a home for UserProfileDTO objects.
 * It extends the BaseDTOHome class and provides methods for creating new instances of UserProfileDTO.
 */
public class UserProfileDTOHome extends BaseDTOHome {

    private static UserProfileDTOHome instance = new UserProfileDTOHome();

    private UserProfileDTOHome() {
    }

    /**
     * Returns the singleton instance of UserProfileDTOHome.
     *
     * @return The singleton instance of UserProfileDTOHome.
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of UserProfileDTO.
     *
     * @return A new instance of UserProfileDTO.
     */
    public IDTO newDTOInstance() {
        return new UserProfileDTO();
    }
}