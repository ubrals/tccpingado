package resources.databases.impl.relacional;

import java.sql.Connection;

public interface ConexaoInterface {
    /**
     * 
     * @return {@link Connection}
     */
    Connection getConnection();
    
    /**
     * Closes connection
     */
    void close();
}
