package jdbcutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class JdbcUtil {

    // driver, url, username, password
    public String dbms;  // derby, mysql, oracle
    public String dbms_properties_file;
    public String database;
    public String username;
    public String password;
    public String url;
    String driver;
    String server;
    int port;

    private static Connection connection;

    // read properties
    public void readProperties() throws IOException {

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream(dbms_properties_file));

            dbms = prop.getProperty("dbms");

            driver = prop.getProperty("driver");
            database = prop.getProperty("database");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            server = prop.getProperty("server");
            port = Integer.parseInt(prop.getProperty("port"));
            System.out.println(prop.toString());
        } catch (IOException ioe) {
            //ioe.printStackTrace();
            throw new IOException("erreur: lire properties fichier: " + dbms_properties_file);
        }

    }

    // return connection
    public Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }

        return newConnection();
    }

    // 
    abstract void initializeDatabase();

    // create new connection from config file
    abstract Connection newConnection() throws SQLException;

    //close connection 
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs, Statement st) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public static void close(ResultSet rs, Statement st, Connection c) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (c != null) {
                        c.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
