import java.util.List;

/**
 * This interface represents an environment that provides access to command line arguments and key-value pairs.
 */
public interface IEnvironment {

    /**
     * Returns an array of command line arguments.
     *
     * @return an array of command line arguments
     */
    String[] getComnandLineArgs();

    /**
     * Returns the value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if the key is not found
     */
    String getValue(String key);

    /**
     * Returns a list of all keys in the environment.
     *
     * @return a list of all keys in the environment
     */
    List<String> getKeys();

    /**
     * Checks if the environment contains the specified key.
     *
     * @param key the key to check
     * @return true if the environment contains the key, false otherwise
     */
    Boolean HasKey(String key);

}