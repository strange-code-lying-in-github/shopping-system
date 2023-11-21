
import java.io.*;
import java.sql.*;

/**
 * The class is a factory for creating instances of the IDbConnectionWrapper
 * interface.
 */
class DbFactory extends IFactory<IDbConnectionWrapper> {
    /**
     * The function returns an instance of the AppLoggingAgent class.
     * 
     * @return The method is returning an instance of the AppLoggingAgent class.
     */
    private static AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());
}
