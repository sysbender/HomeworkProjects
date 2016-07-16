package jdbcutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyJdbcUtil extends JdbcUtil {

    public DerbyJdbcUtil() {
        this.dbms = "derby";
        this.dbms_properties_file = dbms + "_properties.xml";
        //this.driver = "org.apache.derby.jdbc.EmbeddedDriver";      
    }

    @Override
    void initializeDatabase() {
    }

    @Override
    Connection newConnection() throws SQLException {

            Connection connection = null;
            this.url = "jdbc:" + this.dbms + ":" + this.database;
            connection = DriverManager.getConnection(this.url+ ";create=true", username, password);
            return connection;

    }
}
