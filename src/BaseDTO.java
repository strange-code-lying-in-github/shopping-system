import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public abstract class BaseDTO implements IDTO {

    private AppLoggingAgent logging = new AppLoggingAgent(new AppLoggingStream());

    private Map<String, DTOTypes> fields = new HashMap<String, DTOTypes>();
    private Map<Integer, String> fieldsOrderMap = new HashMap<Integer, String>();
    private Map<String, Object> values = new HashMap<String, Object>();
    private String dtoName = "";

    /**
     * @return the dtoName
     */
    @Override
    public String getDtoName() {
        return dtoName;
    }

    private BaseDTO() {
    }

    public BaseDTO(String dtoName, Map<String, DTOTypes> fields, Map<Integer, String> fieldsOrderMap) {
        this.dtoName = dtoName;
        this.fields = fields;
        this.fieldsOrderMap = fieldsOrderMap;
    }

    public BaseDTO(BaseDTO baseDTO) {
        this.fields = baseDTO.fields;
        this.fieldsOrderMap = baseDTO.fieldsOrderMap;
        this.values = baseDTO.values;
        this.dtoName = baseDTO.dtoName;
    }

    public Object getValue(String fieldName) {
        if (values.containsKey(fieldName))
            return values.get(fieldName);
        return null;
    }

    public Boolean setValue(String fieldName, Object value) {
        values.put(fieldName, value);
        return true;
    }

    public Map<String, DTOTypes> getFields() {
        return fields;
    }

    public Boolean updateSchema() {
        return false;
    }

    protected Boolean processLoadResult(ResultSet rs, List<IDTO> loadResults) throws SQLException {
        if (rs == null)
            return false;
        for (Map.Entry<String, DTOTypes> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            DTOTypes fieldType = entry.getValue();
            Object value = getValueFromResultSet(rs, fieldName, fieldType);
            setValue(fieldName, value);
        }
        loadResults.add(this);
        return true;
    }

    protected Object getValueFromResultSet(ResultSet rs, String fieldName, DTOTypes dtoTypes) throws SQLException {
        switch (dtoTypes) {
            case INTEGER:
                return rs.getInt(fieldName);
            case DATE:
                return rs.getDate(fieldName);
            default:
                return rs.getString(fieldName);
        }
    }

    protected String formatValueForQuery(Object value, DTOTypes dtoTypes) {
        try {
            switch (dtoTypes) {
                case INTEGER:
                    return value + "";
                case DATE:
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                    return formatValueForQuery(sdf.format(value), DTOTypes.STRING);
                default:
                    return "'" + value + "'";
            }
        } catch (Exception e) {
            logging.log(value.toString(), e);
            throw e;
        }
    }

    @Override
    public IDbResultProcessor getLoadProcessor() {

        class LoadResultProcessor implements IDbResultProcessor {
            private IDTO dto;

            LoadResultProcessor(IDTO dto) {
                this.dto = dto;
            }

            @Override
            public Boolean process(ResultSet rs) throws SQLException {
                return processLoadResult(rs, loadResults);
            }

            private List<IDTO> loadResults = new ArrayList<>();

            public List<IDTO> getResults() {
                return loadResults;
            }
        }
        return new LoadResultProcessor(this);
    }

    @Override
    public String getLoadSql(String schema) {
        StringBuilder sb = new StringBuilder();
        Boolean isFirst = true;
        sb.append("SELECT * FROM " + schema + "." + this.getDtoName() + " WHERE ");
        for (Map.Entry<String, DTOTypes> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            DTOTypes fieldType = entry.getValue();
            Object value = getValue(fieldName);
            if (value == null)
                continue;

            if (!isFirst)
                sb.append(" AND ");
            else
                isFirst = false;
            sb.append(fieldName + " = " + formatValueForQuery(value, fieldType));
        }
        return sb.toString();
    }

    protected Boolean processSaveResult(ResultSet rs, List<IDTO> saveResults) throws SQLException {
        if (rs == null)
            return false;
        for (Map.Entry<String, DTOTypes> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            DTOTypes fieldType = entry.getValue();
            Object value = getValueFromResultSet(rs, fieldName, fieldType);
            setValue(fieldName, value);
        }
        saveResults.add(this);
        return true;
    }

    @Override
    public IDbResultProcessor getSaveProcessor() {
        class SaveResultProcessor implements IDbResultProcessor {
            private IDTO dto;

            SaveResultProcessor(IDTO dto) {
                this.dto = dto;
            }

            @Override
            public Boolean process(ResultSet rs) throws SQLException {
                return processSaveResult(rs, saveResults);
            }

            private List<IDTO> saveResults = Arrays.asList(this.dto);

            public List<IDTO> getResults() {
                return saveResults;
            }
        }
        return new SaveResultProcessor(this);
    }

    @Override
    public String getSaveSql(String schema) {
        StringBuilder sb = new StringBuilder();
        Object value = getValue("id");
        Boolean isInsert = value == null;

        if (isInsert)
            buildInsertSql(sb, schema);
        else
            buildUpdateSql(sb, schema);

        return sb.toString();
    }

    protected void buildInsertSql(StringBuilder sb, String schema) {
        sb.append("INSERT INTO " + schema + "." + this.getDtoName() + " (");
        for (Map.Entry<String, DTOTypes> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            DTOTypes fieldType = entry.getValue();
            Object value = getValue(fieldName);
            if (value == null)
                continue;
            sb.append(fieldName + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(") VALUES (");
        for (Map.Entry<String, DTOTypes> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            DTOTypes fieldType = entry.getValue();
            Object value = getValue(fieldName);
            if (value == null)
                continue;
            sb.append(formatValueForQuery(value, fieldType) + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(")");
    }

    protected void buildUpdateSql(StringBuilder sb, String schema) {
        sb.append("UPDATE " + schema + "." + this.getDtoName() + " SET ");
        for (Map.Entry<String, DTOTypes> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            DTOTypes fieldType = entry.getValue();
            Object value = getValue(fieldName);
            sb.append(fieldName + " = " + formatValueForQuery(value, fieldType) + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" WHERE id = " + getValue("id"));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getDtoName() + ": ");
        for (Map.Entry<String, DTOTypes> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            DTOTypes fieldType = entry.getValue();
            Object value = getValue(fieldName);
            sb.append(fieldName + " = " + value + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}