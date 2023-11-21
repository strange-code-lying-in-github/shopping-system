
/**
 * The ExecuteQuery class is a subclass of the IDbConnectionOperation interface.
 * It implements the
 * execute method of the IDbConnectionOperation interface.
 */
public class ExecuteQueryOperation implements IDbConnectionOperation {
    public Boolean execute(IDbConnectionWrapper dbConnectionWrapper, String sql, IDbResultProcessor resultProcessor) {
        return dbConnectionWrapper.execute(sql, resultProcessor);
    }
}