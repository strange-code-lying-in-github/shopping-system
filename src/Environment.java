import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The Environment class represents the environment in which the application is running.
 * It provides access to command line arguments, properties, and other environment-related information.
 */
public class Environment implements IEnvironment {
    // simple singleton per instance
    private static IEnvironment _instance = getInstance();

    /**
     * Represents an environment.
     */
    private static IEnvironment getInstance() {
        return new Environment(ProcessHandle.current().info().arguments().orElse(new String[0]));
    }

    /**
     * Represents an interface for the environment.
     */
    public static IEnvironment Instance() {
        return _instance;
    }

    /**
     * The logging agent used for logging application events.
     */
    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());
    /**
     * The properties for the environment.
     */
    private Properties _properties = new Properties(System.getProperties());

    /**
     * The command line arguments passed to the program.
     */
    private String[] args;

    /**
     * The Environment class represents the environment settings for the shopping system.
     * It loads the properties from a file and provides access to them through the _properties map.
     */
    private Environment(String[] args) {
        this.args = args;
        String filename = System.getProperty("PROPERTY_FILE_NAME", "app.properties");
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            Properties prop = new Properties();

            // is stream valid?
            if (input == null) {
                logging.log("Error finding " + filename);
                return;
            }

            // load a properties file from class path
            prop.load(input);
            // overlay properties from file
            prop.forEach((key, value) -> _properties.put(key, value));
            _properties.put("PROPERTY_FILE_NAME", filename);

        } catch (IOException ex) {
            logging.log(ex.getMessage(), ex);
        }
    }

    /**
     * Returns the command line arguments passed to the application.
     *
     * @return an array of strings representing the command line arguments
     */
    @Override
    public String[] getComnandLineArgs() {
        return this.args;
    }

    /**
     * Retrieves the value associated with the specified key from the properties.
     *
     * @param key the key whose associated value is to be retrieved
     * @return the value to which the specified key is mapped, or null if the key is not found
     */
    @Override
    public String getValue(String key) {
        return _properties.getProperty(key, null);
    }

    /**
     * Returns a list of keys in the environment.
     *
     * @return a list of keys
     */
    @Override
    public List<String> getKeys() {
        List<String> keys = new ArrayList<String>();
        _properties.forEach((key, value) -> keys.add((String) key));
        return keys;
    }

    /**
     * Checks if the specified key exists in the environment.
     * 
     * @param key the key to check
     * @return true if the key exists, false otherwise
     */
    @Override
    public Boolean HasKey(String key) {
        return _properties.containsKey(key);
    }

}
