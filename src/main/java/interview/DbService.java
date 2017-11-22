package interview;

import java.sql.SQLException;

public interface DbService {
    public Object loadFromDb(String connectionDetails, String sql, Class type) throws SQLException;
    public Object saveToDatabase(Object data, String connectionDetails) throws SQLException;
    public void delete(String id, Class type, String connectionDetails) throws SQLException;
}

