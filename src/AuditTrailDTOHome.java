
/**
 * The AuditTrailDTOHome class is responsible for managing the creation of AuditTrailDTO objects.
 * It extends the BaseDTOHome class and implements the IDTOHome interface.
 */
public class AuditTrailDTOHome extends BaseDTOHome {

    private static AuditTrailDTOHome instance = new AuditTrailDTOHome();

    private AuditTrailDTOHome() {
    }

    /**
     * Returns the singleton instance of AuditTrailDTOHome.
     *
     * @return the singleton instance of AuditTrailDTOHome
     */
    public static IDTOHome Instance() {
        return instance;
    }

    /**
     * Creates a new instance of AuditTrailDTO.
     *
     * @return a new instance of AuditTrailDTO
     */
    public IDTO newDTOInstance() {
        return new AuditTrailDTO();
    }
}