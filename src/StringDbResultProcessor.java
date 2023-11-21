import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StringDbResultProcessor implements IDbResultProcessor {
    private String result = "";

    public String getResult() {
        return result;
    }

    @Override
    public Boolean process(ResultSet rs) throws SQLException {
        result = DbUtils.resultSetToString(rs);
        return (result != null);
    }

    @Override
    public List<IDTO> getResults() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResults'");
    }
}