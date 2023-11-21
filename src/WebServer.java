import com.sun.net.httpserver.*;

import java.net.InetSocketAddress;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The WebServer class represents a web server that listens for HTTP requests and handles them accordingly.
 */
public class WebServer {

    /**
     * The AppLoggingAgent class represents an agent responsible for logging application events.
     * It provides methods to log messages, errors, and other events.
     */
    protected static AppLoggingAgent logger = new AppLoggingAgent(new AppLoggingStream());
    /**
     * Represents the HTTP server for the shopping system.
     */
    private HttpServer server;
    /**
     * The executor service used for managing threads in the web server.
     */
    private ExecutorService threadPoolExecutor;

    /**
     * The maximum depth of the queue for incoming requests.
     * The value is obtained from the environment variable "QUEUE_DEPTH".
     * If the environment variable is not set, the default value is 100.
     */
    private Integer queueDepth = Integer
            .parseInt(System.getenv().containsKey("QUEUE_DEPTH") ? System.getenv("QUEUE_DEPTH") : "100");
    /**
     * The port number for the web server.
     * If the environment variable "PORT" is set, the port number will be parsed from it.
     * Otherwise, the default port number is 8001.
     */
    private Integer port = Integer.parseInt(System.getenv().containsKey("PORT") ? System.getenv("PORT") : "8001");
    /**
     * The maximum number of threads that the web server can handle.
     * The value is obtained from the environment variable "MAX_THREADS".
     * If the environment variable is not set, the default value is 25.
     */
    private Integer maxThreads = Integer
            .parseInt(System.getenv().containsKey("MAX_THREADS") ? System.getenv("MAX_THREADS") : "25");
    /**
     * The hostname of the web server.
     * It is determined by the value of the "HOST_NAME" environment variable, 
     * or defaults to "0.0.0.0" if the environment variable is not set.
     */
    private String hostname = System.getenv().containsKey("HOST_NAME") ? System.getenv("HOST_NAME") : "0.0.0.0";

    public WebServer() {
        this.threadPoolExecutor = Executors.newFixedThreadPool(maxThreads);
        try {
            this.server = HttpServer.create(new InetSocketAddress(this.hostname, this.port), queueDepth);
            this.server.setExecutor(threadPoolExecutor);
        } catch (IOException e) {
            System.out.println("Error creating server");
        }
    }

    /**
     * Starts the web server.
     */
    public void start() {
        logger.log("Server started");
        try {
            server.start();
            logger.log(" Starting server at => " + this.hostname + ":" + this.port);
        } catch (Exception e) {
            logger.log("Error running server", e);
        }
    }

    /**
     * Adds a route to the web server.
     *
     * @param route   the route to be added
     * @param handler the handler for the route
     * @return the HttpContext object representing the added route
     */
    public HttpContext addRoute(String route, HttpHandler handler) {
        return addRoute(route, handler, null);
    }

    /**
        * Adds a route to the web server with the specified route path, handler, and authenticator.
        * 
        * @param route The route path for the request.
        * @param handler The handler to be executed for the route.
        * @param authenticator The authenticator to be used for the route.
        * @return The HttpContext object representing the added route, or null if an error occurred.
        */
    public HttpContext addRoute(String route, HttpHandler handler, com.sun.net.httpserver.Authenticator authenticator) {
        try {
            HttpContext context = this.server.createContext(route, handler);
            if (authenticator != null) {
                context.setAuthenticator(authenticator);
            }
            return context;
        } catch (Exception e) {
            logger.log("Error adding route", e);
            return null;
        }
    }

    public static void main(String[] args) {
        WebServer server = new WebServer();

        server.addRoute("/", new EchoHandler());
        server.addRoute("/echo/", new EchoHandler());
        server.start();

    }
}
