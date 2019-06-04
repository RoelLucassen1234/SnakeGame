package restData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLConnector {

    private  String drivers;

    private  String url;

    private final static Logger LOGGER = Logger.getLogger(SQLConnector.class.getName());


    private Connection conn;

    private final String dbname = "db.prop";
    public SQLConnector() {
        FileInputStream input = null;
        try  {
            input = new FileInputStream(dbname);
            // load a properties file
            Properties prop = new Properties();
            try {
                prop.load(input);
            } catch (IOException ex) {
                Logger.getLogger(SQLConnector.class.getName()).log(Level.SEVERE, null, ex);
            }

            drivers = prop.getProperty("jdbc.drivers");
            url = prop.getProperty("jdbc.url");


            Class.forName(drivers);

            this.open();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    protected void open() {

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }

    }
    protected PreparedStatement getStatement(String stmt) {
        try {

            return this.conn.prepareStatement(stmt);
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return null;
        }
    }

    protected ResultSet executeQuery(PreparedStatement stmt) {
        try {
            return stmt.executeQuery();
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return null;
        }
    }

    protected int executeUpdate(PreparedStatement stmt) {
        try {
            return stmt.executeUpdate();
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return 0;
    }


    protected void close() {
        try {
            this.conn.close();
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }
}
