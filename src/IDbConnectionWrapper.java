import java.io.Closeable;
import java.io.IOException;

/**
 * The interface is used to create instances of the IDbConnectionWrapper
 * interface.
 */
interface IDbConnectionWrapper extends Closeable {

    @Override
    /**
     * The function closes the connection to the database.
     * 
     * @throws IOException
     */
    void close() throws IOException;

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
    void connectionProperties(String user, String pass, int port, String host, String database, String schema);

    /**
     * Executes a database query and processes the result.
     *
     * @param query           The SQL query to be executed.
     * @param resultProcessor The processor to handle the query result.
     * @return True if the query was successful, false otherwise.
     */
    Boolean query(String query, IDbResultProcessor resultProcessor);

    /**
     * Executes a database query and processes the result.
     *
     * @param query           the SQL query to execute
     * @param resultProcessor the processor to handle the result of the query
     * @return true if the query was successfully executed, false otherwise
     */
    Boolean execute(String query, IDbResultProcessor resultProcessor);
}