
import java.util.HashMap;

/**
 * The MainClass class represents the entry point of the program.
 * It contains a constructor that initializes the args field.
 * It also contains methods for validating user input and executing database
 * operations.
 */
public class MainClass {

    // The line `protected AppLoggingAgent logging = new AppLoggingAgent(new
    // AppLoggingStream());` is
    // creating an instance of the `AppLoggingAgent` class and assigning it to the
    // `logging` variable.
    // The `AppLoggingAgent` class is responsible for handling logging in the
    // program. The `new
    // AppLoggingAgent(new AppLoggingStream())` part is creating a new instance of
    // `AppLoggingAgent`
    // and passing an instance of `AppLoggingStream` as a parameter to its
    // constructor. This suggests
    // that the `AppLoggingAgent` class requires an `AppLoggingStream` object to
    // function properly.
    protected AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    /**
     * The command line arguments passed to the program.
     */
    private String[] args = null;

    private ShoppingSystem shoppingSystem;

    /**
     * The MainClass class represents the entry point of the program.
     * It contains a constructor that initializes the args field.
     */
    public MainClass(String[] args) {
        this.args = args;
    }

    public void start() {
        IEnvironment environment = Environment.Instance();
        this.shoppingSystem = new ShoppingSystem(environment);
        this.shoppingSystem.start();
    }

    /**
     * The main method is the entry point of the program. It creates an instance of
     * MainClass and calls the runHospital method.
     * 
     * @param args an array of command-line arguments for the program
     */
    public static void main(String[] args) {
        MainClass myMainClass = new MainClass(args);
        myMainClass.start();
    }
}
