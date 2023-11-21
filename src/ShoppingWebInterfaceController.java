
/**
 * The ShoppingWebInterfaceController class represents a controller for the
 * shopping web interface.
 * It handles the configuration of routes and provides access to the
 * environment, data layer service,
 * business layer service, and web server.
 */
public class ShoppingWebInterfaceController {
    /**
     * The logging agent used for logging events in the shopping web interface
     * controller.
     */
    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    /**
     * The environment object used by the ShoppingWebInterfaceController.
     */
    private IEnvironment environment;
    /**
     * The shopping data service used by the shopping web interface controller.
     */
    private ShoppingDataLayerService shoppingDataService;
    /**
     * The shopping business layer service used by the web interface controller.
     */
    private ShoppingBusinessLayerService shoppingBusinessService;
    /**
     * The web server used by the shopping web interface controller.
     */
    private WebServer server;

    private ShoppingWebAuthenticator authenticator;

    public ShoppingWebInterfaceController(IEnvironment environment, ShoppingDataLayerService shoppingDataService,
            ShoppingBusinessLayerService shoppingBusinessService, WebServer server) {
        this.environment = environment;
        this.shoppingDataService = shoppingDataService;
        this.shoppingBusinessService = shoppingBusinessService;
        this.server = server;
        this.authenticator = new ShoppingWebAuthenticator(this);
    }

    /**
     * Configures the shopping web interface controller.
     * Adds a route for "/echo/" with the EchoHandler.
     */
    public void configure() {
        this.server.addRoute("/echo/", new EchoHandler(), this.authenticator);
    }

    /**
     * Returns the environment object.
     *
     * @return the environment object
     */
    public IEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Returns the ShoppingDataLayerService instance used by this controller.
     *
     * @return the ShoppingDataLayerService instance
     */
    public ShoppingDataLayerService getShoppingDataService() {
        return this.shoppingDataService;
    }

    /**
     * Returns the ShoppingBusinessLayerService instance used by this controller.
     *
     * @return the ShoppingBusinessLayerService instance
     */
    public ShoppingBusinessLayerService getShoppingBusinessService() {
        return this.shoppingBusinessService;
    }

    /**
     * Returns the WebServer object associated with this controller.
     *
     * @return the WebServer object
     */
    public WebServer getServer() {
        return this.server;
    }

    /**
     * Validates the login credentials of a user.
     * 
     * @param user The username of the user.
     * @param pwd  The password of the user.
     * @return true if the login credentials are valid, false otherwise.
     */
    public Boolean validateLogin(String user, String pwd) {
        return this.shoppingBusinessService.validateLogin(user, pwd);
    }
}