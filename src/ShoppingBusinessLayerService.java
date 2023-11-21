import java.util.List;

/**
 * The ShoppingBusinessLayerService class represents the business layer service
 * for shopping operations.
 * It provides methods for configuring the service, retrieving user information,
 * and validating login credentials.
 */
public class ShoppingBusinessLayerService {

    /**
     * The logging agent used for logging in the shopping business layer service.
     */
    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());
    /**
     * The environment used by the ShoppingBusinessLayerService.
     */
    private IEnvironment environment;
    /**
     * The data layer service for shopping operations.
     */
    private ShoppingDataLayerService shoppingDataService;

    /**
     * Constructs a ShoppingBusinessLayerService object with the specified
     * environment and data layer service.
     *
     * @param environment         The environment object used for configuration.
     * @param shoppingDataService The data layer service used for accessing shopping
     *                            data.
     */
    public ShoppingBusinessLayerService(IEnvironment environment, ShoppingDataLayerService shoppingDataService) {
        this.environment = environment;
        this.shoppingDataService = shoppingDataService;
    }

    /**
     * Configures the shopping business layer service.
     */
    public void configure() {
        // TODO: Implement configuration logic
    }

    /**
     * Retrieves the user information for the specified username.
     *
     * @param username The username of the user.
     * @return The UserDTO object representing the user information.
     */
    public UserDTO getUser(String username) {
        UserDTO user = (UserDTO) UserDTOHome.Instance().newDTOInstance();

        user.setValue("email_address", username);
        List<IDTO> dtos = this.shoppingDataService.loadDTOs(user, user.getDtoHome());

        if (dtos.size() == 0)
            return user;
        return (UserDTO) dtos.getFirst();
    }

    protected void addAuditTrail(String msg, UserDTO userDTO) {
        AuditTrailDTO auditTrailDTO = (AuditTrailDTO) AuditTrailDTOHome.Instance().newDTOInstance();
        auditTrailDTO.setValue("user_id", userDTO.getValue("id"));
        auditTrailDTO.setValue("action", msg);
        this.shoppingDataService.saveDTO(auditTrailDTO);
    }

    /**
     * Validates the login credentials for the specified user and password.
     *
     * @param user The username.
     * @param pwd  The password.
     * @return true if the login credentials are valid, false otherwise.
     */
    public Boolean validateLogin(String user, String pwd) {
        String inputPwdHash = CoreUtils.encodeSha256String(pwd);

        UserDTO userDTO = getUser(user);
        String userName = userDTO.getValue("email_address").toString();
        if (userName == null) {
            String msg = "Login attempt for user: " + user + " with password: " + inputPwdHash + " - Unknown User";
            addAuditTrail(msg, userDTO);
            return false;
        }
        String passwordHash = userDTO.getValue("password_hash").toString();

        Boolean isValid = user.equals(userName) && inputPwdHash.equals(passwordHash);
        String msg = "Login attempt for user: " + user + " with password: " + inputPwdHash + " - "
                + (isValid ? "Success" : "Failure");
        addAuditTrail(msg, userDTO);

        return isValid;
    }

}
