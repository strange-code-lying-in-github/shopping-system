import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

/**
 * This interface represents the information about an HTTP request context.
 */
public interface IHttpRequestContextInfo {

    /**
     * Returns the HttpExchange object associated with the request.
     *
     * @return The HttpExchange object.
     */
    HttpExchange getHttpExchange();

    /**
     * Returns the URI of the request.
     *
     * @return The URI of the request.
     */
    String getUri();

    /**
     * Returns the path component of the URI.
     *
     * @return The path component of the URI.
     */
    String getUriPath();

    /**
     * Returns the query string of the URI.
     *
     * @return The query string of the URI.
     */
    String getQueryString();

    /**
     * Returns a map of the query parameters.
     *
     * @return A map of the query parameters.
     */
    Map<String, String> getQueryMap();

    /**
     * Returns the request method.
     *
     * @return The request method.
     */
    String getRequestMethod();

    /**
     * Returns a map of the request headers.
     *
     * @return A map of the request headers.
     */
    Map<String, List<String>> getHeaders();

    /**
     * Returns a map of the request cookies.
     *
     * @return A map of the request cookies.
     */
    Map<String, String> getCookies();
}