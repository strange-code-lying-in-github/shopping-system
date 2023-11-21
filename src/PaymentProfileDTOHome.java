
/**
 * This class represents a home for PaymentProfileDTO objects.
 * It extends the BaseDTOHome class and implements the IDTOHome interface.
 * It provides methods for creating new instances of PaymentProfileDTO objects.
 */
public class PaymentProfileDTOHome extends BaseDTOHome {

    private static PaymentProfileDTOHome instance = new PaymentProfileDTOHome();

    private PaymentProfileDTOHome() {
    }

    /**
     * Returns the singleton instance of PaymentProfileDTOHome.
     *
     * @return the singleton instance of PaymentProfileDTOHome
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of PaymentProfileDTO.
     *
     * @return a new instance of PaymentProfileDTO
     */
    public IDTO newDTOInstance() {
        return new PaymentProfileDTO();
    }
}