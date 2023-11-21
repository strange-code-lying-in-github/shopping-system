/**
 * The main class representing the shopping system.
 */
public class ShoppingSystem {

    /**
     * The logging agent used for logging application events.
     */
    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    /**
     * Represents a web server for the shopping system.
     */
    private WebServer server;

    /**
     * @return the server
     */
    public WebServer getServer() {
        return server;
    }

    /**
     * Represents the environment for the shopping system.
     * This interface provides methods to interact with the environment.
     */
    private IEnvironment environment;
    /**
     * Represents the service for accessing the data layer of the shopping system.
     */
    private ShoppingDataLayerService shoppingDataService;

    /**
     * @return the shoppingDataService
     */
    public ShoppingDataLayerService getShoppingDataService() {
        return shoppingDataService;
    }

    /**
     * Represents the business layer service for shopping operations.
     */
    private ShoppingBusinessLayerService shoppingBusinessService;

    /**
     * @return the shoppingBusinessService
     */
    public ShoppingBusinessLayerService getShoppingBusinessService() {
        return shoppingBusinessService;
    }

    /**
     * This class represents an adapter for the shopping web interface.
     * It is responsible for handling the communication between the shopping system
     * and the web interface.
     */
    private ShoppingWebInterfaceAdapter shoppingWebInterfaceAdapter;

    /**
     * Constructs a new ShoppingSystem object with the specified environment.
     * 
     * @param environment the environment in which the shopping system operates
     */
    public ShoppingSystem(IEnvironment environment) {
        this.environment = environment;

        this.shoppingDataService = new ShoppingDataLayerService(this.environment);
        this.shoppingBusinessService = new ShoppingBusinessLayerService(this.environment, this.shoppingDataService);
        this.server = new WebServer();
        this.shoppingWebInterfaceAdapter = new ShoppingWebInterfaceAdapter(this.environment, this.shoppingDataService,
                this.shoppingBusinessService, this.server);
    }

    /**
     * Configures the shopping system by configuring the data service, business
     * service, and web interface adapter.
     */
    public void configure() {
        this.shoppingDataService.configure();
        this.shoppingBusinessService.configure();
    }

    /**
     * Starts the shopping system by configuring the data service, business service,
     * web interface adapter, and starting the server.
     */
    public void start() {
        this.configure();
        this.shoppingWebInterfaceAdapter.configure();
        this.server.start();
    }
}