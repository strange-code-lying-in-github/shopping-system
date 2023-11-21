import java.util.List;

public class ShoppingPaymentService {

    /**
     * The logging agent used for logging in the shopping payment service.
     */
    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());
    /**
     * Represents an interface for accessing the environment settings.
     */
    private IEnvironment environment;
    /**
     * The ShoppingBusinessLayerService is responsible for handling the business
     * logic related to shopping.
     */
    private ShoppingBusinessLayerService shoppingBusinessService;

    public ShoppingPaymentService(IEnvironment environment, ShoppingBusinessLayerService shoppingBusinessService) {
        this.environment = environment;
        this.shoppingBusinessService = shoppingBusinessService;
    }

    /**
     * Configures the shopping payment service
     */
    public void configure() {
        // TODO: Implement configuration logic
    }

    /**
     * Processes the payment for a given cart and payment profile.
     * 
     * @param cartId           The ID of the cart.
     * @param paymentProfileId The ID of the payment profile.
     * @return {@code true} if the payment is processed successfully, {@code false}
     *         otherwise.
     */
    public Boolean processPayment(Integer cartId, Integer paymentProfileId) {
        IDTO cart = this.shoppingBusinessService.getCart(cartId);
        if (cart == null) {
            this.shoppingBusinessService.addAuditTrail("cartId: " + cartId + " INVALID cartId",
                    (UserDTO) UserDTOHome.Instance().newDTOInstance());
            logging.log("Order not found");
            return false;
        }
        UserDTO user = (UserDTO) this.shoppingBusinessService.getUser((Integer) cart.getValue("user_id"));
        if (user == null) {
            this.shoppingBusinessService.addAuditTrail("cartId: " + cartId + " INVALID USERID",
                    (UserDTO) UserDTOHome.Instance().newDTOInstance());
            logging.log("User not found");
            return false;
        }
        // Get the user payment method
        IDTO userPayment = this.shoppingBusinessService.getPaymentProfile(paymentProfileId);
        if (userPayment == null) {
            this.shoppingBusinessService.addAuditTrail("cartId: " + cartId + " INVALID PAYMENT PROFILE",
                    user);
            logging.log("User payment method not found");
            return false;
        }

        PurchaseDTO pudto = (PurchaseDTO) PurchaseDTOHome.Instance().newDTOInstance();
        pudto.setValue("user_id", user.getValue("id"));
        pudto.setValue("paymentprofile_id", userPayment.getValue("id"));
        List<IDTO> purchases = this.shoppingBusinessService.saveDTO(pudto);
        pudto = (PurchaseDTO) purchases.getFirst();

        OrderDTO order = (OrderDTO) OrderDTOHome.Instance().newDTOInstance();
        order.setValue("user_id", user.getValue("id"));
        order.setValue("cart_id", cart.getValue("id"));
        order.setValue("purchase_id", pudto.getValue("id"));
        order.setValue("status", "pending");
        List<IDTO> orders = this.shoppingBusinessService.saveDTO(order);
        order = (OrderDTO) orders.getFirst();

        List<IDTO> cartItems = this.shoppingBusinessService.getCartItems(cartId);
        for (IDTO item : cartItems) {
            Integer product_id = (Integer) item.getValue("product_id");
            IDTO product = this.shoppingBusinessService.getProductById(product_id);

            OrderItemDTO oidto = (OrderItemDTO) OrderItemDTOHome.Instance().newDTOInstance();
            oidto.setValue("cartitem_id", item.getValue("id"));
            oidto.setValue("order_id", order.getValue("id"));
            oidto.setValue("price", product.getValue("price"));
            List<IDTO> result = this.shoppingBusinessService.saveDTO(oidto);
            oidto = (OrderItemDTO) result.getFirst();
        }
        // Add an audit trail entry
        this.shoppingBusinessService.addAuditTrail("cartId: " + cartId + " PAYMENT PROCESSED",
                (UserDTO) UserDTOHome.Instance().newDTOInstance());
        return true;
    }
}