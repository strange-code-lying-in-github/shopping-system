import java.io.IOException;
import java.security.MessageDigestSpi;

/**
 * The main class representing the shopping system.
 */
public class ShoppingSystem {

    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    private WebServer server;
    private IEnvironment environment;
    private ShoppingDataLayerService shoppingDataService;
    private ShoppingBusinessLayerService shoppingBusinessService;
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
     * Starts the shopping system by configuring the data service, business service,
     * web interface adapter, and starting the server.
     */
    public void start() {
        this.shoppingDataService.configure();
        this.shoppingBusinessService.configure();
        this.shoppingWebInterfaceAdapter.configure();
        this.server.start();
    }
}