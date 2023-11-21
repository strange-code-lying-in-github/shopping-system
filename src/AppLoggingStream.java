import java.io.PrintStream;

/**
 * The AppLoggingStream class implements the IAppLoggingStream interface and
 * provides
 * methods for logging messages to an output stream. It includes methods for
 * logging
 * strings, objects, and throwables, as well as a method for flushing the output
 * stream.
 */
class AppLoggingStream implements IAppLoggingStream {

    /**
     * The oStream property is an instance of the PrintStream class.
     */
    private PrintStream oStream = System.out;

    /**
     * The log function takes an array of strings as input and prints each string to
     * the output stream,
     * followed by flushing the stream.
     * 
     * @param messages An array of strings containing the messages to be logged.
     */
    public void log(String[] messages) {
        for (String message : messages) {
            this.oStream.println(message);
        }
        this.oStream.flush();
    }

    /**
     * The log function takes an object as input and prints the object's string
     * representation to the
     * output stream, followed by flushing the stream.
     * 
     * @param object The "object" parameter is an instance of the Object class,
     *               which
     *               is the base class for all
     *               classes in Java. It represents an object that can be used to
     *               store any type of data.
     */
    public void log(Object object) {
        this.log(object.toString());
    }

    /**
     * The log function prints a message to the output stream and flushes it.
     * 
     * @param message The message parameter is a string that represents the log
     *                message that you want
     *                to print.
     */
    public void log(String message) {
        this.oStream.println(message);
        this.oStream.flush();
    }

    /**
     * The log function prints the error message and stack trace of a Throwable
     * object to an output
     * stream.
     * 
     * @param throwable The "throwable" parameter is an instance of the Throwable
     *                  class, which
     *                  represents an object that can be thrown and caught. It can
     *                  be any subclass of Throwable, such as
     *                  Exception or Error. In this method, the throwable object is
     *                  used to log an error message and
     *                  stack trace.
     */
    public void log(Throwable throwable) {
        this.log("", throwable);
    }

    /**
     * The log function prints a message, the throwable's message, and the stack
     * trace to the output
     * stream.
     * 
     * @param message   A string message that you want to log. This can be any
     *                  information or description
     *                  that you want to include in the log.
     * @param throwable The "throwable" parameter is an object of type Throwable,
     *                  which represents an
     *                  exception or error that has occurred in the code. It can be
     *                  used to retrieve information about
     *                  the exception, such as its message and stack trace.
     */
    public void log(String message, Throwable throwable) {
        this.oStream.println(message);
        this.oStream.println(throwable.getMessage());
        throwable.printStackTrace(this.oStream);
        this.oStream.flush();
    }

    /**
     * The flush() function flushes the output stream.
     */
    public void flush() {
        this.oStream.flush();
    }
}