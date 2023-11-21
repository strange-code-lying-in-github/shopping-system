import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a Data Transfer Object (DTO) for a product.
 * It extends the BaseDTO class and provides specific fields and field order mappings for the product entity.
 */
public class ProductDTO extends BaseDTO {

    // Fields map for the product entity
    private final static Map<String, DTOTypes> _fields = new TreeMap<String, DTOTypes>() {
        {
            put("id", DTOTypes.INTEGER);
            put("name", DTOTypes.STRING);
            put("description", DTOTypes.STRING);
            put("price", DTOTypes.FLOAT);
            put("date_created", DTOTypes.DATE);
            put("date_modified", DTOTypes.DATE);
        }
    };

    // Field order map for the product entity
    private final static Map<Integer, String> _fieldsOrderMap = new HashMap<Integer, String>() {
        {
            put(0, "id");
            put(1, "name");
            put(2, "description");
            put(3, "price");
            put(4, "date_created");
            put(5, "date_modified");
        }
    };

    /**
     * Constructs a new instance of the ProductDTO class.
     * Initializes the fields and field order mappings for the product entity.
     */
    public ProductDTO() {
        super("product", _fields, _fieldsOrderMap);
    }

    /**
     * Gets the DTO home for the product entity.
     * @return The DTO home for the product entity.
     */
    public IDTOHome getDtoHome() {
        return ProductDTOHome.Instance();
    }
}