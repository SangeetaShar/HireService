package interview;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 43996861 on 22/11/2017.
 */
public class DbServiceImpl implements DbService {
    private static Map<String, Object> dbValues = new HashMap<String, Object>();
    static{
        dbValues.put("reg1", new Car("reg1"));
        dbValues.put("reg2", new Car("reg2"));
        dbValues.put("reg3", new Car("reg3"));
        dbValues.put("reg4", new Car("reg4"));
    }

    @Override
    public Object loadFromDb(String connectionDetails, String sql, Class type) throws SQLException {
        return dbValues.get(sql);
    }

    @Override
    public Object saveToDatabase(Object data, String connectionDetails) throws SQLException {
        if(data instanceof Client){
            dbValues.get(((Client)data).getName());
            dbValues.put(((Client)data).getName(), data);
        }
        if(data instanceof Car){
            dbValues.get(((Car)data).getReg());
            dbValues.put(((Car)data).getReg(), data);
        }
        return data;
    }

    @Override
    public void delete(String id, Class type, String connectionDetails) throws SQLException {
        Object remove = dbValues.remove(id);


    }
}
