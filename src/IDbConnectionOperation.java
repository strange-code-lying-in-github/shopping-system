
/**
 * Represents an operation to be executed on a database connection.
 */
public interface IDbConnectionOperation {
        /**
         * Executes the operation on the specified database connection.
         *
         * @param dbConnectionWrapper The wrapper for the database connection.
         * @param sql                 The SQL statement to be executed.
         * @param resultProcessor     The processor for the result of the operation.
         * @return True if the operation was executed successfully, false otherwise.
         */
        Boolean execute(IDbConnectionWrapper dbConnectionWrapper, String sql, IDbResultProcessor resultProcessor);
}
