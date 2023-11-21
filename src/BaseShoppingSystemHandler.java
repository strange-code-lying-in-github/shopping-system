import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

public abstract class BaseShoppingSystemHandler extends BaseRouteHandler {

    /**
     * The service responsible for handling the business logic of the shopping system.
     */
    private ShoppingBusinessLayerService shoppingBusinessService;

    /**
     * Constructs a BaseShoppingSystemHandler object with the specified shopping
     * business layer service.
     *
     * @param shoppingBusinessService The shopping business layer service.
     */
    public BaseShoppingSystemHandler(ShoppingBusinessLayerService shoppingBusinessService) {
        this.shoppingBusinessService = shoppingBusinessService;
    }

    /**
     * Handles the HTTP request by processing the given HttpExchange object.
     * This method is responsible for logging the request details, handling the request method,
     * building the response, and handling any exceptions that may occur.
     *
     * @param httpExchange The HttpExchange object representing the HTTP request.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String buildResponse = "";
        int responseCode = 500;

        logger.log("httpExchange: " + httpExchange.getRequestMethod() + " " + httpExchange.getRequestURI());

        try {
            final String method = httpExchange.getRequestMethod().toString().toUpperCase();
            String requestParamValue = handleMethodRequest(httpExchange, method);

            buildResponse = buildResponse(requestParamValue);
            logger.log("Response: " + buildResponse);
        } catch (Exception e) {
            logger.log("Error handling request", e);
            buildResponse = buildResponse(e.getMessage());
            responseCode = 500;
        }
        this.handleResponse(httpExchange, buildResponse, responseCode);
    }

    /**
     * Handles the method request based on the HTTP method.
     *
     * @param httpExchange The HTTP exchange object.
     * @param method The HTTP method.
     * @return The response string.
     * @throws Exception If an unsupported HTTP method is provided.
     */
    private String handleMethodRequest(HttpExchange httpExchange, final String method) throws Exception {
        switch (method) {
            case "GET":
                return handleGetRequest(httpExchange);
            case "POST":
                return handlePostRequest(httpExchange);
            default:
                throw new Exception("Unsupported HTTP method: " + method);
        }
    }

    /**
     * @return the shoppingBusinessService
     */
    protected ShoppingBusinessLayerService getShoppingBusinessService() {
        return shoppingBusinessService;
    };

    /**
     * Handles the GET request and returns a response as a String.
     *
     * @param httpExchange the HTTP exchange object representing the request and
     *                     response
     * @return a String representing the response to the GET request
     */
    abstract protected String handleGetRequest(HttpExchange httpExchange);

    /**
     * Handles the POST request and returns a string response.
     *
     * @param httpExchange the HTTP exchange object
     * @return the string response
     */
    abstract protected String handlePostRequest(HttpExchange httpExchange);

}