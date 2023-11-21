import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public abstract class BaseRouteHandler implements IHttpRouteHandler {

    protected AppLoggingAgent logger = new AppLoggingAgent(new AppLoggingStream());

    /**
     * Builds a response string based on the given response string and response type.
     * If the response type is JSON, TXT, or XML, the response string is appended as is.
     * Otherwise, the response string is wrapped in HTML tags.
     *
     * @param responseString The response string to be included in the response.
     * @param responseType   The type of the response (JSON, TXT, XML, etc.).
     * @return The built response string.
     */
    @Override
    public String buildResponse(String responseString, HttpResponseTextType responseType) {
        StringBuilder responseBuilder = new StringBuilder();
        switch (responseType) {
            case JSON:
            case TXT:
            case XML:
                responseBuilder.append(responseString);
                break;
            default:
                responseBuilder.append("<html>").append("<body>")
                        .append(responseString)
                        .append("</body>")
                        .append("</html>");
                break;
        }
        String responseBuilderString = responseBuilder.toString();
        logger.log("[BaseRouteHandler] Response: " + responseBuilderString);
        return responseBuilderString;
    }

    /**
     * Builds a response string.
     *
     * @param responseString The response string to be built.
     * @return The built response string.
     */
    protected String buildResponse(String responseString) {
        return buildResponse(responseString, HttpResponseTextType.HTML);
    }

    /**
     * Handles the HTTP response by sending the specified response string and response code.
     * This method is used internally by the class and should not be called directly.
     *
     * @param httpExchange The HttpExchange object representing the HTTP request and response.
     * @param responseString The string to be sent as the response body.
     * @param responseCode The HTTP response code to be sent.
     * @throws IOException If an I/O error occurs while sending the response.
     */
    protected void handleResponse(HttpExchange httpExchange, String responseString, Integer responseCode)
            throws IOException {
        handleResponse(httpExchange, HttpResponseTextType.HTML, responseString, responseCode);
    }

    /**
     * Handles the HTTP response by setting the response content type, sending the response headers,
     * and writing the response string to the output stream.
     *
     * @param httpExchange   The HTTP exchange object.
     * @param responseType   The type of the response text.
     * @param responseString The response string to be sent.
     * @param responseCode   The HTTP response code.
     * @throws IOException If an I/O error occurs while handling the response.
     */
    protected void handleResponse(HttpExchange httpExchange, HttpResponseTextType responseType, String responseString,
            Integer responseCode)
            throws IOException {

        setResponseContentType(httpExchange, responseType);
        try (OutputStream outputStream = httpExchange.getResponseBody()) {
            httpExchange.sendResponseHeaders(responseCode, responseString.length());
            outputStream.write(responseString.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            logger.log("Error handling response", e);
            throw e;
        }
    }

    /**
     * Sets the response content type for the given HttpExchange object.
     * The content type is set to HttpResponseTextType.HTML by default.
     *
     * @param httpExchange The HttpExchange object for which to set the response content type.
     */
    protected void setResponseContentType(HttpExchange httpExchange) {
        setResponseContentType(httpExchange, HttpResponseTextType.HTML);
    }

    /**
     * Sets the response content type based on the specified HttpResponseTextType.
     *
     * @param httpExchange The HttpExchange object representing the HTTP request and response.
     * @param responseType The HttpResponseTextType indicating the desired response content type.
     */
    protected void setResponseContentType(HttpExchange httpExchange, HttpResponseTextType responseType) {
        Headers h = httpExchange.getResponseHeaders();
        switch (responseType) {
            case JSON:
                h.set("Content-Type", "application/json");
                break;
            case XML:
                h.set("Content-Type", "application/xml");
                break;
            case TXT:
                h.set("Content-Type", "text/plain");
                break;
            default:
                h.set("Content-Type", "text/html");
                break;
        }
    }

    /**
     * Processes the URI of the HTTP request and returns an instance of IHttpRequestContextInfo.
     *
     * @param httpExchange The HttpExchange object representing the HTTP request.
     * @return An instance of IHttpRequestContextInfo containing information about the HTTP request.
     */
    protected IHttpRequestContextInfo processUri(HttpExchange httpExchange) {
        return new HttpRequestContextInfo(httpExchange);
    }

    /**
     * Adds a cookie to the response headers of the given HttpExchange.
     *
     * @param httpExchange The HttpExchange object representing the HTTP request and response.
     * @param cookie The IHttpCookie object representing the cookie to be added.
     */
    protected void addCookie(HttpExchange httpExchange, IHttpCookie cookie) {
        Headers respHeaders = httpExchange.getResponseHeaders();
        respHeaders.add("Set-Cookie", cookie.toString());
    }

    /**
     * Parses the given form data and returns a map of key-value pairs.
     *
     * @param formData the form data to be parsed
     * @return a map containing the parsed key-value pairs
     * @throws UnsupportedEncodingException if the encoding is not supported
     */
    protected static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        return HttpHelpers.parseFormData(formData);
    }

}
