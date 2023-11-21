
/**
 * A utility class for parsing string values to enum constants.
 */
class EnumUtils {
    /**
     * Parses the given string value to the corresponding enum constant of the
     * specified enum type.
     * 
     * @param <T>       the type of the enum
     * @param enumClass the class object of the enum type
     * @param value     the string value to be parsed
     * @return the enum constant of the specified enum type with the specified name
     * @throws IllegalArgumentException if the specified enum type has no constant
     *                                  with the specified name, or the specified
     *                                  class object does not represent an enum type
     */
    public static <T extends Enum<T>> T parse(Class<T> enumClass, String value) {
        return Enum.valueOf(enumClass, value);
    }
}