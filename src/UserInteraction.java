import java.util.Scanner;

/**
 * The UserInteraction class provides methods for interacting with the user
 * through the console.
 */
class UserInteraction {
    /**
     * The function returns an instance of the AppLoggingAgent class.
     * 
     * @return The method is returning an instance of the AppLoggingAgent class.
     */
    private static AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    /**
     * Scanner object used to read input from the console.
     */
    private static Scanner consoleIn = null;
    /**
     * The output stream used for writing to the console.
     */
    // private static PrintStream consoleOut = null;

    static {
        /**
         * Initializes the console input and output streams.
         */
        consoleIn = new Scanner(System.in).useDelimiter(System.getProperty("line.separator"));
        // consoleOut = System.out;
    }

    /**
     * This class represents the user interaction functionality of the program.
     */
    public UserInteraction() {
    }

    /**
     * Prints the given prompt to the console and flushes the output stream.
     * 
     * @param prompt the prompt to be printed to the console
     */
    public static void sendPrompt(String prompt) {
        //// print the given prompt to the console
        // consoleOut.println(prompt);
        //// flush the output stream
        // consoleOut.flush();
        logging.log(prompt);
    }

    /**
     * Prints the given prompt to the console and flushes the output stream.
     * 
     * @param prompt the prompt to be printed to the console
     */
    public static void sendPrompt(String[] prompt) {
        //// print the given prompt to the console
        // consoleOut.println(prompt);
        //// flush the output stream
        // consoleOut.flush();
        logging.log(prompt);
    }

    /**
     * Reads a line of text from the console input and returns it as a string.
     *
     * @return the line of text entered by the user
     */
    public static String getConsoleInputString() {
        return consoleIn.nextLine();
    }

    /**
     * Reads a boolean value from the console input.
     *
     * @return the boolean value read from the console input
     */
    public static Boolean getConsoleInputBoolean() {
        return consoleIn.nextBoolean();
    }

    /**
     * Reads an integer value from the console input.
     *
     * @return the integer value read from the console input
     */
    public static Integer getConsoleInputInteger() {
        return consoleIn.nextInt();
    }

    /**
     * Reads a double value from the console input.
     *
     * @return the double value read from the console input.
     */
    public static Double getConsoleInputDouble() {
        return consoleIn.nextDouble();
    }

    /**
     * Prompts the user for input and returns the input as a String.
     * 
     * @param prompt the message to display to the user as a prompt
     * @return the user's input as a String
     */
    public static String getInput(String prompt) {
        sendPrompt(prompt);
        String nextString = getConsoleInputString();
        sendPrompt(" >> " + nextString);
        return nextString;
    }

    /**
     * Prompts the user for a boolean input using the given prompt message.
     * 
     * @param prompt the message to display to the user as a prompt
     * @return the boolean value entered by the user
     */
    public static Boolean getInputBoolean(String prompt) {
        String value = getInput(prompt);
        if (value.toLowerCase().startsWith("y")) {
            return true;
        }
        if (value.toLowerCase().startsWith("n")) {
            return false;
        }
        Boolean nextBoolean = Boolean.valueOf(value);
        return nextBoolean;
    }

    /**
     * Prompts the user for an input and returns the input as an Integer.
     * 
     * @param prompt the message to display to the user as a prompt
     * @return the user's input as an Integer
     */
    public static Integer getInputInteger(String prompt) {
        Integer nextInt = Integer.valueOf(getInput(prompt));
        return nextInt;
    }

    /**
     * Prompts the user for input and returns the input as a Double.
     *
     * @param prompt the message to display to the user as a prompt
     * @return the user's input as a Double
     */
    public static Double getInputDouble(String prompt) {
        Double nextDouble = Double.valueOf(getInput(prompt));
        return nextDouble;
    }
}