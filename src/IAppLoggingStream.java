
/**
 * The IAppLoggingStream interface defines methods for logging messages and
 * objects.
 */
interface IAppLoggingStream {
    /**
     * The log function is used to print a message to the console.
     * 
     * @param message The parameter "message" is of type String, which means it can
     *                hold a sequence of
     *                characters. It is used to pass a message that you want to log
     *                or display.
     */
    void log(String message);

    /**
     * The log function is used to print an object to the console.
     * 
     * @param object The parameter "object" is of type Object, which means it can
     *               hold any type of
     *               object. It is used to pass an object that you want to log or
     *               display.
     */
    void log(Object object);

    /**
     * The log function takes an array of strings as input and logs each string.
     * 
     * @param messages An array of strings that represents the messages to be
     *                 logged.
     */
    void log(String[] messages);

    /**
     * The log function logs a message and an associated throwable object.
     * 
     * @param message   A string that represents the log message to be logged. This
     *                  can be any
     *                  information or description that you want to include in the
     *                  log.
     * @param throwable The "throwable" parameter is used to pass an exception or
     *                  error object. It
     *                  allows you to log an error message along with the stack
     *                  trace of the exception.
     */
    void log(String message, Throwable throwable);

    /**
     * The log function is used to log an exception or error.
     * 
     * @param throwable The "throwable" parameter is of type Throwable, which is the
     *                  base class for all
     *                  exceptions and errors in Java. It represents an object that
     *                  can be thrown and caught to handle
     *                  exceptional conditions in a program.
     */
    void log(Throwable throwable);

    /**
     * The flush() function is used to clear any buffered output and write it to the
     * output stream.
     */
    void flush();
}