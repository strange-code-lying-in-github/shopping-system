import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDTO {

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
    @Order(1)
    void testUserDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO dto = (UserDTO) UserDTOHome.Instance().newDTOInstance();

        dto.setValue("first_name", "admin");
        dto.setValue("last_name", "admin");
        dto.setValue("email_address", "admin");
        dto.setValue("password_hash", CoreUtils.encodeSha256String("admin"));

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (UserDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(2)
    void testUserDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO dto = (UserDTO) UserDTOHome.Instance().newDTOInstance();

        dto.setValue("first_name", "joe");
        dto.setValue("last_name", "sample");
        dto.setValue("email_address", "joesample");
        dto.setValue("password_hash", CoreUtils.encodeSha256String("joesample"));

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (UserDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(3)
    void testUserDTO_3() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO dto = (UserDTO) UserDTOHome.Instance().newDTOInstance();

        dto.setValue("first_name", "jill");
        dto.setValue("last_name", "sample");
        dto.setValue("email_address", "jillsample");
        dto.setValue("password_hash", CoreUtils.encodeSha256String("jillsample"));

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (UserDTO) result.get(0);
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * 
     * put("id", DTOTypes.INTEGER);
     * put("name", DTOTypes.STRING);
     * put("description", DTOTypes.STRING);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     * }
     */
    @Test
    @Order(4)
    void testRoleDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        RoleDTO dto = (RoleDTO) RoleDTOHome.Instance().newDTOInstance();

        dto.setValue("name", "admin");
        dto.setValue("description", "administrator");

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (RoleDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(5)
    void testRoleDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        RoleDTO dto = (RoleDTO) RoleDTOHome.Instance().newDTOInstance();

        dto.setValue("name", "user");
        dto.setValue("description", "user");

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (RoleDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * put("id", DTOTypes.INTEGER);
     * put("name", DTOTypes.STRING);
     * put("description", DTOTypes.STRING);
     * put("price", DTOTypes.FLOAT);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(6)
    void testProductDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        ProductDTO dto = (ProductDTO) ProductDTOHome.Instance().newDTOInstance();

        dto.setValue("name", "cleaner");
        dto.setValue("description", "soap");
        dto.setValue("price", 10.0);

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (ProductDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(7)
    void testProductDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        ProductDTO dto = (ProductDTO) ProductDTOHome.Instance().newDTOInstance();

        dto.setValue("name", "gasoline");
        dto.setValue("description", "UNL92");
        dto.setValue("price", 4.5);

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (ProductDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(8)
    void testProductDTO_3() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        ProductDTO dto = (ProductDTO) ProductDTOHome.Instance().newDTOInstance();

        dto.setValue("name", "tires");
        dto.setValue("description", "runflats");
        dto.setValue("price", 300.0);

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (ProductDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(9)
    void testProductDTO_4() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        ProductDTO dto = (ProductDTO) ProductDTOHome.Instance().newDTOInstance();

        dto.setValue("name", "car");
        dto.setValue("description", "porsche");
        dto.setValue("price", 300000.0);

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (ProductDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * put("id", DTOTypes.INTEGER);
     * put("name", DTOTypes.STRING);
     * put("description", DTOTypes.STRING);
     * put("type", DTOTypes.STRING);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(10)
    void testPaymentProfileDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        PaymentProfileDTO dto = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();

        dto.setValue("name", "ach");
        dto.setValue("description", "banking");
        dto.setValue("type", "ach");

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (PaymentProfileDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(11)
    void testPaymentProfileDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        PaymentProfileDTO dto = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();

        dto.setValue("name", "cc");
        dto.setValue("description", "creditcard");
        dto.setValue("type", "cc");

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (PaymentProfileDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * put("id", DTOTypes.INTEGER);
     * put("user_id", DTOTypes.INTEGER);
     * put("paymentprofile_id", DTOTypes.INTEGER);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(12)
    void testUserProfileDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "joesample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        PaymentProfileDTO pdto = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();
        pdto.setValue("name", "ach");
        List<IDTO> profiles = dataLayerService.loadDTOs(pdto, pdto.getDtoHome());
        pdto = (PaymentProfileDTO) profiles.getFirst();

        UserProfileDTO dto = (UserProfileDTO) UserProfileDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("paymentprofile_id", pdto.getValue("id"));

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (UserProfileDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(13)
    void testUserProfileDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "jillsample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        PaymentProfileDTO pdto = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();
        pdto.setValue("name", "cc");
        List<IDTO> profiles = dataLayerService.loadDTOs(pdto, pdto.getDtoHome());
        pdto = (PaymentProfileDTO) profiles.getFirst();

        UserProfileDTO dto = (UserProfileDTO) UserProfileDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("paymentprofile_id", pdto.getValue("id"));

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (UserProfileDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * put("id", DTOTypes.INTEGER);
     * put("user_id", DTOTypes.INTEGER);
     * put("paymentprofile_id", DTOTypes.INTEGER);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(14)
    void testPurchaseDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "joesample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        PaymentProfileDTO pdto = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();
        pdto.setValue("name", "ach");
        List<IDTO> profiles = dataLayerService.loadDTOs(pdto, pdto.getDtoHome());
        pdto = (PaymentProfileDTO) profiles.getFirst();

        PurchaseDTO dto = (PurchaseDTO) PurchaseDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("paymentprofile_id", pdto.getValue("id"));

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (PurchaseDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(15)
    void testPurchaseDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "jillsample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        PaymentProfileDTO pdto = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();
        pdto.setValue("name", "cc");
        List<IDTO> profiles = dataLayerService.loadDTOs(pdto, pdto.getDtoHome());
        pdto = (PaymentProfileDTO) profiles.getFirst();

        PurchaseDTO dto = (PurchaseDTO) PurchaseDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("paymentprofile_id", pdto.getValue("id"));

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (PurchaseDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * put("id", DTOTypes.INTEGER);
     * put("user_id", DTOTypes.INTEGER);
     * put("status", DTOTypes.STRING);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(16)
    void testCartDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "joesample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        CartDTO dto = (CartDTO) CartDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("status", "active");

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (CartDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(17)
    void testCartDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "jillsample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        CartDTO dto = (CartDTO) CartDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("status", "complete");

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (CartDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * 
     * put("id", DTOTypes.INTEGER);
     * put("cart_id", DTOTypes.INTEGER);
     * put("product_id", DTOTypes.INTEGER);
     * put("quantity", DTOTypes.INTEGER);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(18)
    void testCartItemDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "joesample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        CartDTO cdto = (CartDTO) CartDTOHome.Instance().newDTOInstance();
        cdto.setValue("user_id", udto.getValue("id"));
        cdto.setValue("status", "active");
        List<IDTO> carts = dataLayerService.loadDTOs(cdto, cdto.getDtoHome());
        cdto = (CartDTO) carts.getFirst();

        ProductDTO pdto = (ProductDTO) ProductDTOHome.Instance().newDTOInstance();
        pdto.setValue("name", "car");
        List<IDTO> products = dataLayerService.loadDTOs(pdto, pdto.getDtoHome());
        pdto = (ProductDTO) products.getFirst();

        CartItemDTO dto = (CartItemDTO) CartItemDTOHome.Instance().newDTOInstance();
        dto.setValue("cart_id", cdto.getValue("id"));
        dto.setValue("product_id", pdto.getValue("id"));
        dto.setValue("quantity", 2);

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (CartItemDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(19)
    void testCartItemDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "joesample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        CartDTO cdto = (CartDTO) CartDTOHome.Instance().newDTOInstance();
        cdto.setValue("user_id", udto.getValue("id"));
        cdto.setValue("status", "active");
        List<IDTO> carts = dataLayerService.loadDTOs(cdto, cdto.getDtoHome());
        cdto = (CartDTO) carts.getFirst();

        ProductDTO pdto = (ProductDTO) ProductDTOHome.Instance().newDTOInstance();
        pdto.setValue("name", "gasoline");
        List<IDTO> products = dataLayerService.loadDTOs(pdto, pdto.getDtoHome());
        pdto = (ProductDTO) products.getFirst();

        CartItemDTO dto = (CartItemDTO) CartItemDTOHome.Instance().newDTOInstance();
        dto.setValue("cart_id", cdto.getValue("id"));
        dto.setValue("product_id", pdto.getValue("id"));
        dto.setValue("quantity", 10);

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (CartItemDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * put("id", DTOTypes.INTEGER);
     * put("user_id", DTOTypes.INTEGER);
     * put("role_id", DTOTypes.INTEGER);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(20)
    void testUserRoleDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "joesample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        RoleDTO rdto = (RoleDTO) RoleDTOHome.Instance().newDTOInstance();
        rdto.setValue("name", "user");
        List<IDTO> roles = dataLayerService.loadDTOs(rdto, rdto.getDtoHome());
        rdto = (RoleDTO) roles.getFirst();

        UserRoleDTO dto = (UserRoleDTO) UserRoleDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("role_id", rdto.getValue("id"));

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (UserRoleDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(21)
    void testUserRoleDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "admin");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        RoleDTO rdto = (RoleDTO) RoleDTOHome.Instance().newDTOInstance();
        rdto.setValue("name", "admin");
        List<IDTO> roles = dataLayerService.loadDTOs(rdto, rdto.getDtoHome());
        rdto = (RoleDTO) roles.getFirst();

        UserRoleDTO dto = (UserRoleDTO) UserRoleDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("role_id", rdto.getValue("id"));

        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (UserRoleDTO) result.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * put("id", DTOTypes.INTEGER);
     * put("purchase_id", DTOTypes.INTEGER);
     * put("cart_id", DTOTypes.INTEGER);
     * put("user_id", DTOTypes.INTEGER);
     * put("status", DTOTypes.STRING);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(22)
    void testOrderDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "joesample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        CartDTO cdto = (CartDTO) CartDTOHome.Instance().newDTOInstance();
        cdto.setValue("user_id", udto.getValue("id"));
        cdto.setValue("status", "active");
        List<IDTO> carts = dataLayerService.loadDTOs(cdto, cdto.getDtoHome());
        cdto = (CartDTO) carts.getFirst();

        PaymentProfileDTO padto = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();
        padto.setValue("name", "ach");
        List<IDTO> profiles = dataLayerService.loadDTOs(padto, padto.getDtoHome());
        padto = (PaymentProfileDTO) profiles.getFirst();

        PurchaseDTO pudto = (PurchaseDTO) PurchaseDTOHome.Instance().newDTOInstance();
        pudto.setValue("user_id", udto.getValue("id"));
        pudto.setValue("paymentprofile_id", padto.getValue("id"));
        List<IDTO> purchases = dataLayerService.loadDTOs(pudto, pudto.getDtoHome());
        pudto = (PurchaseDTO) purchases.getFirst();

        OrderDTO dto = (OrderDTO) OrderDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("cart_id", cdto.getValue("id"));
        dto.setValue("purchase_id", pudto.getValue("id"));
        dto.setValue("status", "active");
        List<IDTO> orders = dataLayerService.saveDTO(dto);
        dto = (OrderDTO) orders.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    @Test
    @Order(23)
    void testOrderDTO_2() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "joesample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        CartDTO cdto = (CartDTO) CartDTOHome.Instance().newDTOInstance();
        cdto.setValue("user_id", udto.getValue("id"));
        cdto.setValue("status", "active");
        List<IDTO> carts = dataLayerService.loadDTOs(cdto, cdto.getDtoHome());
        cdto = (CartDTO) carts.getFirst();

        PaymentProfileDTO pdto = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();
        pdto.setValue("name", "cc");
        List<IDTO> profiles = dataLayerService.loadDTOs(pdto, pdto.getDtoHome());
        pdto = (PaymentProfileDTO) profiles.getFirst();

        PurchaseDTO pudto = (PurchaseDTO) PurchaseDTOHome.Instance().newDTOInstance();
        pudto.setValue("user_id", udto.getValue("id"));
        pudto.setValue("paymentprofile_id", pdto.getValue("id"));
        List<IDTO> purchases = dataLayerService.saveDTO(pudto);
        pudto = (PurchaseDTO) purchases.getFirst();

        OrderDTO dto = (OrderDTO) OrderDTOHome.Instance().newDTOInstance();
        dto.setValue("user_id", udto.getValue("id"));
        dto.setValue("cart_id", cdto.getValue("id"));
        dto.setValue("purchase_id", pudto.getValue("id"));
        dto.setValue("status", "active");
        List<IDTO> orders = dataLayerService.saveDTO(dto);
        dto = (OrderDTO) orders.getFirst();
        logging.log(dto);

        assertNotNull(dto.getValue("id"));
    }

    /*
     * put("id", DTOTypes.INTEGER);
     * put("cartitem_id", DTOTypes.INTEGER);
     * put("order_id", DTOTypes.INTEGER);
     * put("price", DTOTypes.FLOAT);
     * put("date_created", DTOTypes.DATE);
     * put("date_modified", DTOTypes.DATE);
     */
    @Test
    @Order(24)
    void testOrderItemDTO_1() {
        ShoppingDataLayerService dataLayerService = new ShoppingDataLayerService(Environment.Instance());
        dataLayerService.configure();

        UserDTO udto = (UserDTO) UserDTOHome.Instance().newDTOInstance();
        udto.setValue("email_address", "joesample");
        List<IDTO> users = dataLayerService.loadDTOs(udto, udto.getDtoHome());
        udto = (UserDTO) users.getFirst();

        CartDTO cdto = (CartDTO) CartDTOHome.Instance().newDTOInstance();
        cdto.setValue("user_id", udto.getValue("id"));
        cdto.setValue("status", "active");
        List<IDTO> carts = dataLayerService.loadDTOs(cdto, cdto.getDtoHome());
        cdto = (CartDTO) carts.getFirst();

        ProductDTO pdto = (ProductDTO) ProductDTOHome.Instance().newDTOInstance();
        pdto.setValue("name", "car");
        List<IDTO> products = dataLayerService.loadDTOs(pdto, pdto.getDtoHome());
        pdto = (ProductDTO) products.getFirst();

        PaymentProfileDTO padto = (PaymentProfileDTO) PaymentProfileDTOHome.Instance().newDTOInstance();
        padto.setValue("name", "ach");
        List<IDTO> profiles = dataLayerService.loadDTOs(padto, padto.getDtoHome());
        padto = (PaymentProfileDTO) profiles.getFirst();

        PurchaseDTO pudto = (PurchaseDTO) PurchaseDTOHome.Instance().newDTOInstance();
        pudto.setValue("user_id", udto.getValue("id"));
        pudto.setValue("paymentprofile_id", padto.getValue("id"));
        List<IDTO> purchases = dataLayerService.loadDTOs(pudto, pudto.getDtoHome());
        pudto = (PurchaseDTO) purchases.getFirst();

        CartItemDTO cidto = (CartItemDTO) CartItemDTOHome.Instance().newDTOInstance();
        cidto.setValue("cart_id", cdto.getValue("id"));
        cidto.setValue("product_id", pdto.getValue("id"));
        cidto.setValue("quantity", 2);
        List<IDTO> cartitems = dataLayerService.loadDTOs(cidto, cidto.getDtoHome());
        cidto = (CartItemDTO) cartitems.getFirst();

        OrderDTO odto = (OrderDTO) OrderDTOHome.Instance().newDTOInstance();
        odto.setValue("user_id", udto.getValue("id"));
        odto.setValue("cart_id", cdto.getValue("id"));
        odto.setValue("purchase_id", pudto.getValue("id"));
        odto.setValue("status", "active");
        List<IDTO> orders = dataLayerService.loadDTOs(odto, odto.getDtoHome());
        odto = (OrderDTO) orders.getFirst();

        OrderItemDTO dto = (OrderItemDTO) OrderItemDTOHome.Instance().newDTOInstance();
        dto.setValue("cartitem_id", cidto.getValue("id"));
        dto.setValue("order_id", odto.getValue("id"));
        dto.setValue("price", 125000.0);
        List<IDTO> result = dataLayerService.saveDTO(dto);
        dto = (OrderItemDTO) result.getFirst();

        logging.log(dto);
        assertNotNull(dto.getValue("id"));
    }

}