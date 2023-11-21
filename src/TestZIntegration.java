import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestZIntegration {

    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    /*
     * put("id", DTOTypes.INTEGER);
     * put("first_name", DTOTypes.STRING);
     * put("last_name", DTOTypes.STRING);
     * put("email_address", DTOTypes.STRING);
     * put("password_hash", DTOTypes.STRING);
     * put("active", DTOTypes.STRING);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(25)
    void testIntegration_GetProductCatalog() {
        // get catalog
        ShoppingSystem system = new ShoppingSystem(Environment.Instance());
        system.configure();
        ShoppingBusinessLayerService shoppingBusinessService = system.getShoppingBusinessService();

        IProductCatalog catalog = shoppingBusinessService.getProductCatalog();
        for (IDTO product : catalog.getProducts()) {
            logging.log(product.toString());
        }
        assertNotEquals(0l, (long) catalog.getProducts().size());
    }

    @Test
    @Order(26)
    void testIntegration_GetBasket() {
        // get catalog
        ShoppingSystem system = new ShoppingSystem(Environment.Instance());
        system.configure();
        ShoppingBusinessLayerService shoppingBusinessService = system.getShoppingBusinessService();

        UserDTO user = shoppingBusinessService.getUser("joesample");
        logging.log(user.toString());
        assertNotNull(user.getValue("id"));

        IDTO basket = shoppingBusinessService.getActiveCart((Integer) user.getValue("id"));
        logging.log(basket.toString());
        assertNotNull(basket.getValue("id"));
    }

    @Test
    @Order(27)
    void testIntegration_AddToBasket() {
        // get catalog
        ShoppingSystem system = new ShoppingSystem(Environment.Instance());
        system.configure();
        ShoppingBusinessLayerService shoppingBusinessService = system.getShoppingBusinessService();

        UserDTO user = shoppingBusinessService.getUser("joesample");
        logging.log(user.toString());
        assertNotNull(user.getValue("id"));

        IDTO basket = shoppingBusinessService.getActiveCart((Integer) user.getValue("id"));
        logging.log(basket.toString());
        assertNotNull(basket.getValue("id"));

        IProductCatalog catalog = shoppingBusinessService.getProductCatalog();
        for (IDTO product : catalog.getProducts()) {
            logging.log(product.toString());
            String name = (String) product.getValue("name");
            if (name.equals("tires")) {
                CartItemDTO dto = (CartItemDTO) CartItemDTOHome.Instance().newDTOInstance();
                dto.setValue("cart_id", basket.getValue("id"));
                dto.setValue("product_id", product.getValue("id"));
                dto.setValue("quantity", 4);

                List<IDTO> result = shoppingBusinessService.saveDTO(dto);
                dto = (CartItemDTO) result.getFirst();
                assertNotNull(dto.getValue("id"));
            }
        }
        IDTO userProfile = shoppingBusinessService.getUserProfiles((Integer) user.getValue("id")).getFirst();
        ShoppingPaymentService paymentService = shoppingBusinessService.getShoppingPaymentService();
        assertTrue(paymentService.processPayment((Integer) basket.getValue("id"),
                (Integer) userProfile.getValue("id")));
    }

}