import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This interface represents a processor for database query results.
 */
public interface IDbResultProcessor {
    /**
     * Processes the given ResultSet.
     *
     * @param rs the ResultSet to be processed
     * @return true if the processing is successful, false otherwise
     * @throws SQLException if an error occurs while processing the ResultSet
     */
    Boolean process(ResultSet rs) throws SQLException;

    /**
     * Returns the list of results obtained from the processing.
     *
     * @return the list of results
     */
    List<IDTO> getResults();
}
