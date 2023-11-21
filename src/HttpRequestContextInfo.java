import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

/**
 * Represents the context information of an HTTP request.
 */
public class HttpRequestContextInfo implements IHttpRequestContextInfo {

    private HttpExchange httpExchange;
    private String uri;
    private String uriPath;
    private String queryString;
    private Map<String, String> queryMap;
    private Map<String, List<String>> headers;
    private Map<String, String> cookies;
    private String requestMethod;

    public HttpRequestContextInfo(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;

        this.uri = httpExchange.getRequestURI().toString();
        this.uriPath = HttpHelpers.getPath(this.uri);
        this.queryString = HttpHelpers.getQueryString(this.uri);
        this.queryMap = HttpHelpers.getQueryMap(this.queryString);

        Headers headers = httpExchange.getRequestHeaders();
        this.headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (String header : headers.keySet()) {
            this.headers.put(header, headers.get(header));
        }
        this.cookies = this.headers.containsKey("Cookie") ? HttpHelpers.parseCookies(this.headers.get("Cookie"))
                : new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.requestMethod = httpExchange.getRequestMethod();
    }

    @Override
    public HttpExchange getHttpExchange() {
        return this.httpExchange;
    }

    @Override
    public String getUri() {
        return this.uri;
    }

    @Override
    public String getUriPath() {
        return this.uriPath;
    }

    @Override
    public String getQueryString() {
        return this.queryString;
    }

    @Override
    public Map<String, String> getQueryMap() {
        return this.queryMap;
    }

    @Override
    public String getRequestMethod() {
        return this.requestMethod;
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    @Override
    public Map<String, String> getCookies() {
        return this.cookies;
    }

}