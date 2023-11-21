import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The `IFactory` class is a generic factory class that allows the creation of
 * instances of a specified
 * class type.
 */
class IFactory<T> {
    /**
     *
     */
    private static final Map<String, Class<?>> factoryTable = new HashMap<>();

    /**
     * The function returns an instance of the AppLoggingAgent class.
     * 
     * @return The method is returning an instance of the AppLoggingAgent class.
     */
    private static AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    /**
     * The function returns an instance of the IFactory class.
     */
    protected IFactory() {
    }

    static {
        try {
            // force loading of all classes in the path directory
            loadViaClassloader(IFactory.class.getClassLoader(), ".");
        } catch (Exception e) {
            // log or throw exception.
            logging.log(e);
        }
    }

    /**
     * The function registers a class with the factory.
     * 
     * @param registrationName The parameter "registrationName" is a string that
     *                         represents the name of the
     *                         class to be registered. It is used to register a
     *                         class with the factory.
     * @param instance         The parameter "instance" is a Class object that
     *                         represents the class to be
     *                         registered. It is used to register a class with the
     *                         factory.
     */
    public static <T> void register(String registrationName, Class<T> instance) {
        if (registrationName != null && instance != null) {
            factoryTable.put(registrationName, instance);
        }
    }

    /**
     * The function returns an instance of the IFactory class.
     * 
     * @param factoryString The parameter "factoryString" is a string that
     *                      represents the name of the
     *                      class to be created. It is used to create an instance of
     *                      the IFactory class with the
     *                      specified class type.
     */
    public static <T> T create(String factoryString) {
        logging.log("factoryString: " + factoryString);

        if (factoryTable.containsKey(factoryString)) {
            try {
                // have to force load so the static instance registration is called
                Class<?> clazz = factoryTable.get(factoryString);
                return (T) clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                logging.log(e);
            }
        }
        return null;
    }

    /**
     * The function converts the content of an InputStream into a String using the
     * specified character
     * encoding.
     * 
     * @param input  The input parameter is an InputStream, which represents the
     *               input stream from which
     *               the data will be read. This can be any type of input stream,
     *               such as a FileInputStream or a
     *               URLConnection.getInputStream().
     * @param string The "string" parameter in the method represents the character
     *               encoding to be used
     *               when converting the input stream to a string. It specifies the
     *               character set that should be used
     *               to interpret the bytes from the input stream and convert them
     *               into characters.
     * @return The method is returning a String.
     */
    public static <T> String convertUrlToString(final InputStream input, final String string) throws IOException {

        try (InputStreamReader inputStreamReader = new InputStreamReader(input, string)) {
            try (StringWriter stringWriter = new StringWriter()) {
                char[] buffer = new char[AppConstants.READ_BUFFER_SIZE];
                return transferReaderToWriter(inputStreamReader, stringWriter, buffer);
            }
        }
    }

    /**
     * The function transfers data from an InputStreamReader to a StringWriter using
     * a buffer.
     * 
     * @param inputReader The "inputReader" parameter is an InputStreamReader
     *                    object, which is
     *                    used to read characters
     *                    from an input stream. It is the source from which
     *                    characters
     *                    will be read.
     * @param strWriter   The parameter "strWriter" is a StringWriter object that is
     *                    used to
     *                    write the data read from
     *                    the InputStreamReader.
     * @param buffer      The `buffer` parameter is an array of characters that is
     *                    used
     *                    to store the data
     *                    read from the `InputStreamReader` and write it to the
     *                    `StringWriter`. It is used as a temporary
     *                    storage for the data during the transfer process.
     * @return The method is returning a String.
     */
    private static String transferReaderToWriter(InputStreamReader inputReader, StringWriter strWriter, char[] buffer)
            throws IOException {
        return transferReaderToWriter(inputReader, strWriter, buffer, 0);
    }

    /**
     * The function transfers data from an InputStreamReader to a StringWriter and
     * returns the
     * resulting string.
     * 
     * @param inputReader The "inputReader" parameter is an InputStreamReader, which
     *                    is used to
     *                    read characters from an
     *                    InputStream. It is the input source from which characters
     *                    will
     *                    be read.
     * @param strWriter   The parameter "strWriter" is a StringWriter object. It is
     *                    used to
     *                    write the contents read
     *                    from the InputStreamReader.
     * @param buffer      The `buffer` parameter is a character array used to store
     *                    the
     *                    data read from the
     *                    `InputStreamReader` and write to the `StringWriter`. It is
     *                    used
     *                    to read and write data in
     *                    chunks, rather than one character at a time, which can
     *                    improve
     *                    performance. The size of the
     *                    buffer determines how
     * @param numBytes    The parameter "numBytes" is an integer that represents the
     *                    number of
     *                    characters read from the
     *                    input stream. It is used to keep track of the number of
     *                    characters read in each iteration of the
     *                    while loop.
     * @return The method is returning a String.
     */
    private static String transferReaderToWriter(InputStreamReader inputReader, StringWriter strWriter, char[] buffer,
            int numBytes)
            throws IOException {
        while (AppConstants.EOF != (numBytes = inputReader.read(buffer))) {
            strWriter.write(buffer, 0, numBytes);
        }
        return strWriter.toString();
    }

    /**
     * The function loads classes from a specified package path using a class
     * loader.
     * 
     * @param classLoader The "classLoader" parameter is a ClassLoader object that
     *                    is used to
     *                    load classes and resources.
     *                    It is responsible for finding and loading classes at
     *                    runtime.
     * @param packagePath The packagePath parameter is a string representing the
     *                    path to a package in
     *                    the file system. It should be in the format of a file
     *                    system path, using forward slashes ("/")
     *                    to separate directories.
     */
    private static void loadViaClassloader(ClassLoader classLoader, String packagePath) throws Exception {
        String[] classList = getClassList(classLoader, packagePath);
        for (String p : classList) {
            processPath(p);
        }
    }

    /**
     * The function takes a ClassLoader and a package path, retrieves the list of
     * classes in that
     * package, and returns an array of class names.
     * 
     * @param classLoader The "classLoader" parameter is the ClassLoader object that
     *                    is used
     *                    to load classes and
     *                    resources. It is responsible for finding and loading
     *                    classes at runtime.
     * @param packagePath The packagePath parameter is a string representing the
     *                    path of the package
     *                    containing the classes you want to retrieve. It should be
     *                    in the format of a file system path,
     *                    using forward slashes ("/") to separate directories.
     * @return The method is returning an array of strings.
     */
    private static String[] getClassList(ClassLoader classLoader, String packagePath) throws IOException {
        String cleanPackagePath = packagePath.replaceAll("[/]", ".");
        URL classPackageUrl = classLoader.getResource(cleanPackagePath);
        URLConnection connection = classPackageUrl.openConnection();

        String classList = convertUrlToString(connection.getInputStream(), "UTF-8");
        return (classList != null) ? classList.split("\n") : new String[0];
    }

    /**
     * The function returns an array of strings representing the names of the
     * registered classes.
     * 
     * @return The method is returning an array of strings.
     */
    public static String[] getRegistered() {
        List<String> registered = new ArrayList<String>(factoryTable.keySet());
        return registered.toArray(new String[registered.size()]);
    }

    /**
     * The function processes a given path and loads a class if the path ends with
     * ".class" and starts
     * with "DbFactoryW_".
     * 
     * @param strPath The parameter "strPath" is a string representing a file path.
     */
    private static void processPath(String strPath) throws ClassNotFoundException {
        if (strPath.endsWith(".class") && strPath.startsWith(AppConstants.DB_CLASSNAME_PREFIX)) {
            logging.log("loading class: " + strPath);
            Class.forName(strPath.substring(0, strPath.lastIndexOf('.')));
        }
    }

    /*
     * The class returns a static instance of the IFactory class.
     * Uses an old technique to delay allocation until the static instance is called
     * but is thread safe given the static nature of the class.
     * - I learned it was known as the Bill Pugh Singleton Implementation after all
     * these years.
     */
    private static final class InstanceHolder<T> {
        static <T> IFactory<T> INSTANCE() {
            return new IFactory<>();
        }
    }

    /**
     * The function returns an instance of the IFactory class.
     * 
     * @return The method is returning an instance of the IFactory class.
     */
    public static <T> IFactory<T> Instance() {
        return InstanceHolder.INSTANCE();
    }
}