
/**
 * The ShoppingWebInterfaceAdapter class is responsible for adapting the web interface to the shopping system.
 * It provides a convenient interface for configuring and interacting with the web interface controller.
 */
public class ShoppingWebInterfaceAdapter {

    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());
    private ShoppingWebInterfaceController controller;

    /**
     * Constructs a new ShoppingWebInterfaceAdapter with the specified environment, shopping data service,
     * shopping business service, and web server.
     *
     * @param environment         the environment for the shopping system
     * @param shoppingDataService the shopping data service for accessing shopping data
     * @param shoppingBusinessService the shopping business service for performing business logic operations
     * @param server              the web server for hosting the web interface
     */
    public ShoppingWebInterfaceAdapter(IEnvironment environment, ShoppingDataLayerService shoppingDataService,
                                       ShoppingBusinessLayerService shoppingBusinessService, WebServer server) {
        this.controller = new ShoppingWebInterfaceController(environment, shoppingDataService, shoppingBusinessService,
                server);
    }

    /**
     * Configures the web interface by calling the configure method of the web interface controller.
     */
    public void configure() {
        this.controller.configure();
    }
}