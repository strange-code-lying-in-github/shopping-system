import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * The AppLoggingAgent class implements the IAppLoggingStream interface and
 * provides methods for logging messages, objects, arrays of messages, and
 * throwable objects to the application stream. It also provides a method for
 * flushing the output stream.
 */
class AppLoggingAgent implements IAppLoggingStream {

    /**
     * The `appStream` property is an instance of the `IAppLoggingStream` interface.
     */
    public IAppLoggingStream appStream;

    /**
     * @param appStream
     */
    public AppLoggingAgent(IAppLoggingStream appStream) {
        this.appStream = appStream;
    }

    /**
     * Returns the calling method information as a string.
     *
     * @return the calling method information
     */
    private static String getCallingMethodInfo() {
        Thread currentThread = Thread.currentThread();
        StackTraceElement stack = currentThread.getStackTrace()[4];

        StringBuffer sb = new StringBuffer();
        sb
                .append(stack.getClassName())
                .append(".")
                .append(stack.getMethodName())
                .append(":")
                .append(stack.getLineNumber())
                .append(" | ")
                .append(currentThread.threadId());

        return sb.toString();
    }

    /**
     * Returns a string representation of the log header.
     *
     * @return the log header string
     */
    private static String getLogHeaderString() {
        StringBuffer sb = new StringBuffer();
        sb
                .append(Instant.now().toString())
                .append(" | ")
                .append(getCallingMethodInfo())
                .append(" | ");

        return sb.toString();
    }

    /**
     * The log function logs a message to the application stream.
     * 
     * @param message The message parameter is a string that represents the log
     *                message that you want
     *                to log.
     */
    public void log(String message) {
        this.appStream.log(getLogHeaderString() + message);
    }

    /**
     * The log function logs an object to the application stream.
     * 
     * @param object The "object" parameter is an instance of the Object class,
     *               which
     *               is the base class for all
     *               classes in Java. It represents an object that can be used to
     *               store any type of data.
     */
    public void log(Object object) {
        this.appStream.log(getLogHeaderString() + object.toString());
    }

    /**
     * The log function takes an array of messages and logs them using the
     * _appStream object.
     * 
     * @param messages The "messages" parameter is an array of strings. It is used
     *                 to pass multiple log
     *                 messages to the "log" method.
     */
    public void log(String[] messages) {
        ArrayList<String> logMessages = new ArrayList<String>();
        for (String message : messages) {
            logMessages.add(getLogHeaderString() + message);
        }
        this.appStream.log(logMessages);
    }

    /**
     * The log function logs a throwable object to the app stream.
     * 
     * @param throwable The "throwable" parameter is an instance of the Throwable
     *                  class, which
     *                  represents an object that can be thrown and caught. It is
     *                  typically used to handle exceptions
     *                  and errors in Java.
     */
    public void log(Throwable throwable) {
        this.appStream.log(getLogHeaderString(), throwable);
    }

    /**
     * The log function logs a message and a throwable object.
     * 
     * @param message   The message parameter is a string that represents the log
     *                  message to be logged.
     *                  It can contain any information that you want to include in
     *                  the log, such as error messages,
     *                  debug information, or general information about the state of
     *                  the application.
     * @param throwable The "throwable" parameter is used to pass an exception or
     *                  error object. It
     *                  allows you to log an exception along with a message.
     */
    public void log(String message, Throwable throwable) {
        this.appStream.log(getLogHeaderString() + message, throwable);
    }

    /**
     * The flush() function flushes the output stream.
     */
    public void flush() {
        this.appStream.flush();
    }
}