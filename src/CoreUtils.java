import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * The CoreUtils class provides utility methods for encoding strings using SHA-256 algorithm.
 */
public class CoreUtils {

    /**
     * The logging agent used for logging application events.
     */
    private static AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    private CoreUtils() {
    }

    /**
     * A lock object used for synchronization when accessing the digest.
     */
    private static Object digestLock = new Object();

    /**
     * Encodes a string using SHA-256 algorithm and returns the encoded string.
     *
     * @param unencodedString the string to be encoded
     * @return the encoded string
     * @throws RuntimeException if an error occurs during encoding
     */
    public static String encodeSha256String(final String unencodedString) {
        try {
            return bytesToHex(encodeSha256Bytes(unencodedString));
        } catch (Exception ex) {
            logging.log("Error encoding string", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * Encodes a string using SHA-256 algorithm and returns the encoded bytes.
     *
     * @param unencodedString the string to be encoded
     * @return the encoded bytes
     * @throws RuntimeException if an error occurs during encoding
     */
    public static byte[] encodeSha256Bytes(final String unencodedString) {
        try {
            synchronized (digestLock) {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                return digest.digest(unencodedString.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception ex) {
            logging.log("Error encoding string", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * Converts a byte array to a hexadecimal string representation.
     *
     * @param hash the byte array to be converted
     * @return the hexadecimal string representation of the byte array
     */
    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}