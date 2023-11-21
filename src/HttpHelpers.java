import java.util.TreeMap;

import org.apache.commons.text.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * The HttpHelpers class provides utility methods for working with HTTP requests
 * and responses.
 */
public class HttpHelpers {
    /**
     * The environment instance used by the HttpHelpers class.
     */
    private static IEnvironment env = Environment.Instance();

    private HttpHelpers() {
    }

    /**
     * Returns the path component of a given request URI.
     * The path component is the part of the URI before the query string.
     *
     * @param requestUri the request URI
     * @return the path component of the request URI
     */
    public static String getPath(String requestUri) {
        final String[] parts = requestUri.split("\\?");
        return parts[0];
    }

    /**
     * Returns the query string from the given request URI.
     *
     * @param requestUri the request URI
     * @return the query string
     */
    public static String getQueryString(String requestUri) {
        final String[] parts = requestUri.split("\\?");
        if (parts.length > 1) {
            return parts[1];
        }
        return "";
    }

    /**
     * Parses a query string and returns a map of key-value pairs.
     *
     * @param query the query string to parse
     * @return a map containing the key-value pairs from the query string
     */
    public static Map<String, String> getQueryMap(String query) {
        final String[] params = query.split("&");
        Map<String, String> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        for (String param : params) {
            final String[] parts = param.split("=");
            String name = parts[0];
            String value = parts.length > 1 ? parts[1] : "";
            map.put(name, value);
        }
        return map;
    }

    /**
     * Escapes the given response string using the specified text type.
     *
     * @param responseString The response string to escape.
     * @return The escaped response string.
     */
    public static String escapeResponseString(String responseString) {
        return escapeResponseString(responseString, HttpResponseTextType.HTML);
    }

    /**
     * Escapes the given response string based on the specified response type.
     *
     * @param responseString The response string to be escaped.
     * @param responseType   The type of the response (HTML, JSON, XML, TXT).
     * @return The escaped response string.
     */
    public static String escapeResponseString(String responseString, HttpResponseTextType responseType) {
        switch (responseType) {
            case HTML:
                return StringEscapeUtils.escapeHtml4(responseString);
            case JSON:
                return StringEscapeUtils.escapeJson(responseString);
            case XML:
                return StringEscapeUtils.escapeXml11(responseString);
            case TXT:
                return responseString;
            default:
                return StringEscapeUtils.escapeHtml4(responseString);
        }
    }

    /**
     * Parses a list of cookies and returns a map of cookie names to their
     * corresponding values.
     *
     * @param list The list of cookies to be parsed.
     * @return A map of cookie names to their corresponding values.
     */
    public static Map<String, String> parseCookies(List<String> list) {
        Map<String, String> cookies = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        for (String cookie : list) {
            parseCookies(cookie, cookies);
        }
        return cookies;
    }

    /**
     * Parses the cookie string and populates the provided map with the extracted
     * cookies.
     *
     * @param cookieString The string containing the cookies in the format
     *                     "key1=value1; key2=value2; ..."
     * @param cookies      The map to store the extracted cookies, where the key is
     *                     the cookie name and the value is the cookie value.
     */
    private static void parseCookies(String cookieString, Map<String, String> cookies) {
        final String[] cookiePairs = cookieString.split("; ");
        for (String cookiePair : cookiePairs) {
            final String[] cookieValue = cookiePair.split("=");
            String key = cookieValue[0];
            String value = cookieValue.length > 1 ? cookieValue[1] : "";
            cookies.put(key, value);
        }
    }

    /**
     * Parses the given form data and returns a map of key-value pairs.
     * 
     * @param formData the form data to be parsed
     * @return a map containing the parsed key-value pairs
     * @throws UnsupportedEncodingException if the encoding is not supported
     */
    public static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new TreeMap<>();

        final String[] pairs = formData.split("&");
        for (String pair : pairs) {
            final String[] keyValue = pair.split("=");
            String value = URLDecoder.decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
