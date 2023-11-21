import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The ShoppingDataLayerService class is responsible for handling data layer
 * operations
 * related to shopping functionality.
 */
public class ShoppingDataLayerService {

    /**
     * The logging agent used for logging in the shopping data layer service.
     */
    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    static {
        // preload this
        IFactory dbFactory = DbFactory.Instance();
    }

    /**
     * Represents an interface for accessing the environment settings.
     */
    private IEnvironment environment;
    /**
     * The driver used for the shopping data layer service.
     */
    private String driver;
    /**
     * The username associated with the shopping data layer service.
     */
    private String userName;
    /**
     * The password used for authentication.
     */
    private String password;
    /**
     * The host of the shopping data layer service.
     */
    private String host;
    /**
     * The name of the database used by the shopping data layer service.
     */
    private String database;
    /**
     * The port number used by the ShoppingDataLayerService.
     */
    private int port;

    /**
     * The schema used for the shopping data layer service.
     */
    private String schema;

    public ShoppingDataLayerService(IEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Configures the shopping data layer service.
     * Loads the configuration based on the current environment.
     */
    public void configure() {
        loadConfiguration(this.environment);
    }

    /**
     * Loads the configuration values from the given environment object.
     * Sets the driver, username, password, host, database, and port for the
     * database connection.
     *
     * @param environment the environment object containing the configuration values
     */
    private void loadConfiguration(IEnvironment environment) {
        this.driver = environment.getValue("db.driver");
        this.userName = environment.getValue("db.user");
        this.password = environment.getValue("db.password");
        this.host = environment.getValue("db.host");
        this.database = environment.getValue("db.database");
        this.port = Integer.parseInt(environment.getValue("db.port"));
        this.schema = environment.getValue("db.schema");
    }

    /**
     * Executes a database command and returns a Boolean indicating the success or
     * failure of the operation.
     *
     * @param sql             The SQL command to be executed.
     * @param resultProcessor The result processor for handling the query result.
     * @return {@code true} if the command is executed successfully, {@code false}
     *         otherwise.
     */
    public List<IDTO> executeCommand(String sql, IDbResultProcessor resultProcessor) {
        try (IDbConnectionWrapper dbConnectionWrapper = DbFactory.create(this.driver)) {
            getDatabaseConnectionInformation(dbConnectionWrapper);
            Boolean result = (new ExecuteQueryOperation()).execute(dbConnectionWrapper, sql, resultProcessor);
            if (result) {
                return resultProcessor.getResults();
            }
        } catch (Exception e) {
            logging.log("Error executing command", e);
        }
        return new ArrayList<IDTO>();
    }

    /**
     * Executes a SQL query and processes the result using the specified
     * IDbResultProcessor.
     * 
     * @param sql             The SQL query to execute.
     * @param resultProcessor The IDbResultProcessor to process the query result.
     * @return true if the query was executed successfully, false otherwise.
     */
    public List<IDTO> executeQuery(String sql, IDbResultProcessor resultProcessor) {
        try (IDbConnectionWrapper dbConnectionWrapper = DbFactory.create(this.driver)) {
            getDatabaseConnectionInformation(dbConnectionWrapper);
            Boolean result = (new SimpleQueryOperation()).execute(dbConnectionWrapper, sql, resultProcessor);
            if (result)
                return resultProcessor.getResults();

        } catch (Exception e) {
            logging.log("Error executing command", e);
        }
        return new ArrayList<IDTO>();
    }

    /**
     * Prompts the user to enter database connection information and sets the
     * connection properties
     * for the given IDbConnectionWrapper object.
     *
     * @param dbConnectionWrapper the IDbConnectionWrapper object to set the
     *                            connection properties for
     */
    private void getDatabaseConnectionInformation(IDbConnectionWrapper dbConnectionWrapper) {
        dbConnectionWrapper.connectionProperties(this.userName, this.password, this.port, this.host, this.database,
                this.schema);
    }

    /**
     * Loads the specified DTO using the provided SQL query and processor.
     *
     * @param dto The DTO to be loaded.
     * @return True if the DTO was successfully loaded, false otherwise.
     */
    public List<IDTO> loadDTOs(IDTO dto, IDTOHome dtoHome) {
        return executeQuery(dto.getLoadSql(this.schema), dtoHome.getLoadProcessor());
    }

    /**
     * Saves the given DTO object.
     *
     * @param dto The DTO object to be saved.
     * @return True if the DTO object is successfully saved, false otherwise.
     */
    public List<IDTO> saveDTO(IDTO dto) {
        List<IDTO> result = executeCommand(dto.getSaveSql(this.schema), dto.getSaveProcessor());
        for (IDTO dtoResult : result) {
            return loadDTOs(dto, dto.getDtoHome());
        }
        return new ArrayList<IDTO>();
    }

    /**
     * Saves a list of IDTO objects.
     * 
     * @param dtos The list of IDTO objects to be saved.
     * @return The same list of IDTO objects.
     */
    public List<IDTO> saveDTOs(List<IDTO> dtos) {
        for (IDTO dto : dtos) {
            if (saveDTO(dto).size() == 0)
                logging.log("Error saving DTO: " + dto.getDtoName() + " " + dto.toString());
        }
        return dtos;
    }
}