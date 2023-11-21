
/**
 * This class contains constants used throughout the application.
 */
class AppConstants {
    /**
     * The default host name to use if the HOST_NAME environment variable is not
     * set.
     */
    final static String HOST_NAME = System.getenv().containsKey("HOST_NAME") ? System.getenv("HOST_NAME")
            : "localhost";

    /**
     * *NOTE* the db classname prefix for the factory
     */
    final static String DB_CLASSNAME_PREFIX = "DbBaseFactoryWrapper_";

    final static int EOF = -1;
    final static int READ_BUFFER_SIZE = (1024 * 4);
}