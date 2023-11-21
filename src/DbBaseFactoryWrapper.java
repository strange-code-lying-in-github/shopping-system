import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The class is a factory for creating instances of the IDbConnectionWrapper
 * interface.
 */
abstract class DbBaseFactoryWrapper extends IFactory<IDbConnectionWrapper> implements IDbConnectionWrapper {
    /**
     * The function returns an instance of the AppLoggingAgent class.
     * 
     * @return The method is returning an instance of the AppLoggingAgent class.
     */
    protected static AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    private String username;

    /**
     * The function returns the username for the database.
     * 
     * @return The method is returning the username for the database.
     */
    public String getUsername() {
        return username;
    }

    /**
     * The function sets the username for the database.
     * 
     * @param username The username for the database.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    private String password;

    /**
     * The function returns the password for the database.
     * 
     * @return The method is returning the password for the database.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The function sets the password for the database.
     * 
     * @param password The password for the database.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    private String host;

    /**
     * The function returns the host for the database.
     * 
     * @return The method is returning the host for the database.
     */
    public String getHost() {
        return host;
    }

    /**
     * The function sets the host for the database.
     * 
     * @param host The host for the database.
     */
    public void setHost(String host) {
        this.host = host;
    }

    private String database;

    /**
     * The function returns the database name.
     * 
     * @return The method is returning the database name.
     */
    public String getDatabase() {
        return database;
    }

    /**
     * The function sets the database name.
     * 
     * @param database The database name.
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    private int port;

    /**
     * The function returns the port for the database.
     * 
     * @return The method is returning the port for the database.
     */
    public int getPort() {
        return port;
    }

    /**
     * The function sets the port for the database.
     * 
     * @param port The port for the database.
     */
    public void setPort(int port) {
        this.port = port;
    }

    private String url;

    /**
     * The function returns the url for the database.
     * 
     * @return The method is returning the url for the database.
     */
    public String getUrl() {
        return url;
    }

    /**
     * The function sets the url for the database.
     * 
     * @param url The url for the database.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    private String schema;

    /**
     * @return the schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @param schema the schema to set
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    public DbBaseFactoryWrapper() {
    }

    /**
     * The function sets the connection properties.
     * 
     * @param user     The username for the database.
     * @param pass     The password for the database.
     * @param port     The port for the database.
     * @param host     The host for the database.
     * @param database The database name.
     * @param schema   The schema name.
     */
    public void connectionProperties(String user, String pass, int port, String host, String database, String schema) {
        this.username = user;
        this.password = pass;
        this.port = port;
        this.host = host;
        this.database = database;
        this.schema = schema;

        this.url = buildJdbcUrl();
        logging.log("URL: " + this.url);
    }

    /**
     * The function builds the jdbc url for the database.
     * 
     * @return The method is returning the jdbc url for the database.
     */
    public abstract String buildJdbcUrl();

    @Override
    /**
     * The function closes the connection to the database.
     * 
     * @throws IOException
     */
    public void close() throws IOException {
        logging.log(this.getClass().getName() + " closed");
    }

    /**
     * Executes the given query on the database and processes the result using the
     * provided IDbResultProcessor.
     * 
     * @param query           The SQL query to be executed.
     * @param resultProcessor The IDbResultProcessor implementation to process the
     *                        query result.
     * @return true if the query was executed successfully, false otherwise.
     */
    public Boolean query(String query, IDbResultProcessor resultProcessor) {
        logging.log("username: " + username);
        logging.log("password: " + password.length());
        logging.log("query: " + query);

        try (Connection con = DriverManager.getConnection(
                url, this.username, this.password)) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            return resultProcessor.process(rs);
        } catch (SQLException e) {
            logging.log(e);
        }
        // logging.log(response);
        return false;
    }

    /**
     * Executes the given query and processes the result using the provided
     * IDbResultProcessor.
     * 
     * @param query           the SQL query to execute
     * @param resultProcessor the IDbResultProcessor to process the result
     * @return the execution status of the query as a Boolean
     */
    public Boolean execute(String query, IDbResultProcessor resultProcessor) {
        logging.log("username: " + username);
        logging.log("password: " + password.length());
        logging.log("query: " + query);

        try (Connection con = DriverManager.getConnection(
                url, this.username, this.password)) {

            Statement stmt = con.createStatement();
            Boolean status = stmt.execute(query);
            logging.log("query execute status: " + status);
            if (status) {
                ResultSet rs = stmt.getResultSet();
                return resultProcessor.process(rs);
            }
            return true;
        } catch (SQLException e) {
            logging.log(e);
        }
        // logging.log(response);
        return false;
    }
}