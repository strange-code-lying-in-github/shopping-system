import com.sun.net.httpserver.HttpHandler;

/**
 * This interface represents a handler for HTTP routes.
 * It extends the HttpHandler interface.
 */
public interface IHttpRouteHandler extends HttpHandler {
    /**
     * Builds the response string based on the given response string and response type.
     *
     * @param responseString The response string to be built.
     * @param responseType   The type of the response text.
     * @return The built response string.
     */
    String buildResponse(String responseString, HttpResponseTextType responseType);
}