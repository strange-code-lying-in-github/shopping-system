
/**
 * The SimpleQuery class is a subclass of the IDbConnectionOperation interface.
 * It implements the
 * execute method of the IDbConnectionOperation interface.
 */
public class SimpleQueryOperation implements IDbConnectionOperation {
    /**
     * Executes a query operation on the database.
     * 
     * @param dbConnectionWrapper The database connection wrapper.
     * @param sql The SQL query to execute.
     * @param resultProcessor The result processor for handling the query result.
     * @return True if the query was executed successfully, false otherwise.
     */
    public Boolean execute(IDbConnectionWrapper dbConnectionWrapper, String sql,
            IDbResultProcessor resultProcessor) {
        return dbConnectionWrapper.query(sql, resultProcessor);
    }
}