import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class DbUtils {
    /**
     * The function returns an instance of the AppLoggingAgent class.
     * 
     * @return The method is returning an instance of the AppLoggingAgent class.
     */
    private static AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    private DbUtils() {
    }

    /**
     * The function converts a ResultSet to a String.
     * 
     * @param rs The ResultSet to be converted.
     * @return The method is returning the ResultSet as a String.
     */
    public static String resultSetToString(ResultSet rs) {
        StringBuilder sb = new StringBuilder("");
        List<String[]> rows = new ArrayList<String[]>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();

            while (rs.next()) {
                String[] row = new String[columns];
                for (int i = 1; i <= columns; i++) {
                    row[i - 1] = rs.getString(i);
                }
                rows.add(row);
            }
        } catch (SQLException e) {
            logging.log(e);
        }

        for (String[] row : rows) {
            sb
                    .append(String.join(", ", row))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}