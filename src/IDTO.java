import java.util.Map;

/**
 * The IDTO interface represents a Data Transfer Object (DTO) in the shopping
 * system.
 * It defines methods for retrieving SQL statements and result processors for
 * loading and saving data.
 */
public interface IDTO {


    /**
     * Returns the name of the DTO (Data Transfer Object).
     *
     * @return the name of the DTO
     */
    String getDtoName();

    /**
     * Gets the SQL statement for loading data from the database.
     *
     * @return The SQL statement for loading data.
     */
    String getLoadSql(String schema);

    /**
     * Gets the SQL statement for saving data to the database.
     *
     * @return The SQL statement for saving data.
     */
    String getSaveSql(String schema);

    /**
     * Returns a map of fields in the DTO object.
     *
     * @return a map containing the fields of the DTO object
     */
    Map<String, DTOTypes> getFields();

    /**
     * Retrieves the value of a field specified by the given field name.
     *
     * @param fieldName the name of the field
     * @return the value of the field
     */
    Object getValue(String fieldName);

    /**
     * Sets the value of a field in the DTO.
     *
     * @param fieldName the name of the field
     * @param value the value to be set
     * @return true if the value was successfully set, false otherwise
     */
    Boolean setValue(String fieldName, Object value);


    /**
     * Returns the IDbResultProcessor associated with this IDTO.
     *
     * @return the IDbResultProcessor associated with this IDTO
     */
    IDbResultProcessor getLoadProcessor();

    /**
     * Returns the IDbResultProcessor associated with this IDTO.
     *
     * @return the IDbResultProcessor associated with this IDTO
     */
    IDbResultProcessor getSaveProcessor();

    /**
     * This interface represents the home for IDTO objects.
     * It provides methods to interact with IDTO objects.
     */
    IDTOHome getDtoHome();
}