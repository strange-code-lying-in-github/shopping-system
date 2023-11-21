import java.util.List;

/**
 * This interface represents a home for Data Transfer Objects (DTOs).
 * It provides methods for querying and saving DTOs, as well as creating new instances of DTOs.
 */
public interface IDTOHome {
    /**
     * Queries the DTOs based on the provided DTO query.
     *
     * @param dtoQuery The DTO query used for filtering the results.
     * @return A list of DTOs that match the query.
     */
    List<IDTO> queryDTOs(IDTO dtoQuery);

    /**
     * Saves a single DTO.
     *
     * @param dto The DTO to be saved.
     * @return True if the DTO is successfully saved, false otherwise.
     */
    Boolean saveDTO(IDTO dto);

    /**
     * Saves a list of DTOs.
     *
     * @param dtos The list of DTOs to be saved.
     * @return True if all DTOs are successfully saved, false otherwise.
     */
    Boolean saveDTOs(List<IDTO> dtos);

    /**
     * Creates a new instance of the DTO.
     *
     * @return A new instance of the DTO.
     */
    IDTO newDTOInstance();

    /**
     * Gets the result processor for loading data from the database.
     *
     * @return The result processor for loading data from the database.
     */
    IDbResultProcessor getLoadProcessor();
}