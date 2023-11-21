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
     * @return the shoppingDataService
     */
    public ShoppingDataLayerService getShoppingDataService() {
        return shoppingDataService;
    }

    /**
     * The shopping payment service used by the shopping business layer.
     */
    private ShoppingPaymentService shoppingPaymentService;

    /**
     * @return the shoppingPaymentService
     */
    public ShoppingPaymentService getShoppingPaymentService() {
        return shoppingPaymentService;
    }

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

        this.shoppingPaymentService = new ShoppingPaymentService(this.environment, this);
    }

    /**
     * Configures the shopping business layer service.
     */
    public void configure() {
        this.shoppingPaymentService.configure();
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

    /**
     * Adds an audit trail entry with the specified message and user information.
     * 
     * @param msg     The message to be logged in the audit trail.
     * @param userDTO The UserDTO object representing the user associated with the
     *                action.
     */
    protected void addAuditTrail(String msg, UserDTO userDTO) {
        AuditTrailDTO auditTrailDTO = (AuditTrailDTO) AuditTrailDTOHome.Instance().newDTOInstance();
        auditTrailDTO.setValue("user_id", userDTO.getValue("id"));
        auditTrailDTO.setValue("action", msg);
        this.shoppingDataService.saveDTO(auditTrailDTO);
    }

    /**
     * Retrieves the product catalog.
     *
     * @return The product catalog.
     */
    public IProductCatalog getProductCatalog() {
        List<IDTO> dtos = this.shoppingDataService.loadDTOs(ProductDTOHome.Instance().newDTOInstance(),
                ProductDTOHome.Instance());
        return new ProductCatalog(dtos);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The product with the specified ID, or an empty product if not found.
     */
    public IDTO getProductById(Integer productId) {
        ProductDTO product = (ProductDTO) ProductDTOHome.Instance().newDTOInstance();
        product.setValue("id", productId);
        List<IDTO> dtos = this.shoppingDataService.loadDTOs(product, product.getDtoHome());
        if (dtos.size() == 0)
            return product;
        return (ProductDTO) dtos.getFirst();
    }

    /**
     * Retrieves the UserDTO object for the given user ID.
     *
     * @param userId The ID of the user.
     * @return The UserDTO object representing the user.
     */
    public IDTO getUser(Integer userId) {
        UserDTO user = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        user.setValue("id", userId);
        List<IDTO> dtos = this.shoppingDataService.loadDTOs(user, user.getDtoHome());
        if (dtos.size() == 0)
            return user;
        return (UserDTO) dtos.getFirst();
    }

    /**
     * Retrieves a list of roles.
     *
     * @return A list of IDTO objects representing the roles.
     */
    public List<IDTO> getRoles() {
        return this.shoppingDataService.loadDTOs(RoleDTOHome.Instance().newDTOInstance(), RoleDTOHome.Instance());
    }

    /**
     * Retrieves the roles associated with a user.
     * 
     * @param userId The ID of the user.
     * @return A list of IDTO objects representing the user roles.
     */
    public List<IDTO> getUserRoles(Integer userId) {
        UserRoleDTO userRole = (UserRoleDTO) UserRoleDTOHome.Instance().newDTOInstance();
        userRole.setValue("user_id", userId);
        return this.shoppingDataService.loadDTOs(userRole, userRole.getDtoHome());
    }

    /**
     * Retrieves the active cart for a given user.
     *
     * @param userId the ID of the user
     * @return a list of active cart items
     */
    public IDTO getActiveCart(Integer userId) {
        return this.getCartByStatus(userId, "active").getFirst();
    }

    /**
     * Retrieves a list of payment profiles.
     *
     * @return A list of payment profiles.
     */
    public List<IDTO> getPaymentProfiles() {
        PaymentProfileDTO paymentProfile = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();
        return this.shoppingDataService.loadDTOs(paymentProfile, paymentProfile.getDtoHome());
    }

    /**
     * Retrieves the payment profile with the specified ID.
     * 
     * @param paymentProfileId The ID of the payment profile to retrieve.
     * @return The payment profile with the specified ID, or a new instance of
     *         PaymentProfileDTO if no profile is found.
     */
    public IDTO getPaymentProfile(Integer paymentProfileId) {
        PaymentProfileDTO paymentProfile = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();
        paymentProfile.setValue("id", paymentProfileId);
        List<IDTO> dtos = this.shoppingDataService.loadDTOs(paymentProfile, paymentProfile.getDtoHome());
        if (dtos.size() == 0)
            return paymentProfile;
        return (PaymentProfileDTO) dtos.getFirst();
    }

    /**
     * Retrieves the user profiles associated with the given user ID.
     *
     * @param userId The ID of the user.
     * @return A list of user profiles.
     */
    public List<IDTO> getUserProfiles(Integer userId) {
        UserProfileDTO userProfile = (UserProfileDTO) UserProfileDTOHome.Instance().newDTOInstance();
        userProfile.setValue("user_id", userId);
        return this.shoppingDataService.loadDTOs(userProfile, userProfile.getDtoHome());
    }

    /**
     * Retrieves a list of cart items based on the user ID and status.
     *
     * @param userId the ID of the user
     * @param status the status of the cart items
     * @return a list of cart items that match the given user ID and status
     */
    public List<IDTO> getCartByStatus(Integer userId, String status) {
        CartDTO cart = (CartDTO) CartDTOHome.Instance().newDTOInstance();
        cart.setValue("user_id", userId);
        cart.setValue("status", status);
        return this.shoppingDataService.loadDTOs(cart, cart.getDtoHome());
    }

    /**
     * Retrieves the cart with the specified cart ID.
     *
     * @param cartId The ID of the cart to retrieve.
     * @return The cart with the specified ID, or an empty cart if no cart is found.
     */
    public IDTO getCart(Integer cartId) {
        CartDTO cart = (CartDTO) CartDTOHome.Instance().newDTOInstance();
        cart.setValue("id", cartId);
        List<IDTO> dtos = this.shoppingDataService.loadDTOs(cart, cart.getDtoHome());
        if (dtos.size() == 0)
            return cart;
        return (CartDTO) dtos.getFirst();
    }

    /**
     * Retrieves the list of cart items for a given cart ID.
     * 
     * @param cartId The ID of the cart.
     * @return The list of cart items.
     */
    public List<IDTO> getCartItems(Integer cartId) {
        CartItemDTO cartItem = (CartItemDTO) CartItemDTOHome.Instance().newDTOInstance();
        cartItem.setValue("cart_id", cartId);
        return this.shoppingDataService.loadDTOs(cartItem, cartItem.getDtoHome());
    }

    /**
     * Retrieves the UserDTO object based on the provided email address.
     *
     * @param email The email address of the user.
     * @return The UserDTO object representing the user with the provided email
     *         address.
     */
    public IDTO getUserDTO(String email) {
        UserDTO user = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        user.setValue("email_address", email);
        List<IDTO> dtos = this.shoppingDataService.loadDTOs(user, user.getDtoHome());
        if (dtos.size() == 0)
            return user;
        return (UserDTO) dtos.getFirst();
    }

    public IDTO getOrder(Integer orderId) {
        OrderDTO order = (OrderDTO) OrderDTOHome.Instance().newDTOInstance();
        order.setValue("id", orderId);
        List<IDTO> dtos = this.shoppingDataService.loadDTOs(order, order.getDtoHome());
        if (dtos.size() == 0)
            return order;
        return (OrderDTO) dtos.getFirst();
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

    /**
     * Saves the given DTO object.
     * 
     * @param dto The DTO object to be saved.
     * @return The list of saved DTO objects.
     */
    public List<IDTO> saveDTO(IDTO dto) {
        this.addAuditTrail("saving record: " + dto.getDtoName(), (UserDTO) UserDTOHome.Instance().newDTOInstance());
        return this.shoppingDataService.saveDTO(dto);
    }

    /**
     * Loads a list of DTOs based on the given DTO object.
     *
     * @param dto The DTO object used to load the DTOs.
     * @return A list of IDTO objects representing the loaded DTOs.
     */
    public List<IDTO> loadDTOs(IDTO dto) {
        return this.shoppingDataService.loadDTOs(dto, dto.getDtoHome());
    }

}
