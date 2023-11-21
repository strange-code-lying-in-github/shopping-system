
/**
 * The ShoppingWebAuthenticator class is responsible for authenticating user credentials
 * for the shopping web interface.
 */
public class ShoppingWebAuthenticator extends com.sun.net.httpserver.BasicAuthenticator {
    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    private ShoppingWebInterfaceController shoppingWebInterfaceController;

    /**
     * Constructs a new ShoppingWebAuthenticator with the specified ShoppingWebInterfaceController.
     *
     * @param shoppingWebInterfaceController The ShoppingWebInterfaceController instance.
     */
    public ShoppingWebAuthenticator(ShoppingWebInterfaceController shoppingWebInterfaceController) {
        super("ShoppingWebAuthenticator");
        this.shoppingWebInterfaceController = shoppingWebInterfaceController;
    }

    /**
     * Checks the provided user credentials for authentication.
     *
     * @param user The username.
     * @param pwd The password.
     * @return true if the credentials are valid, false otherwise.
     */
    @Override
    public boolean checkCredentials(String user, String pwd) {
        // @todo - add lookup code to pull user/pwdhash from database
        return this.shoppingWebInterfaceController.validateLogin(user, pwd);
    }
}