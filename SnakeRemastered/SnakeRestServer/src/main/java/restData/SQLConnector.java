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

    private Connection conn;

    public SQLConnector() {
        FileInputStream input = null;
        try {
            input = new FileInputStream("db.prop");
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
            e.printStackTrace();
        }
    }

    protected void open() {

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    protected PreparedStatement getStatement(String stmt) {
        try {

            return this.conn.prepareStatement(stmt);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected ResultSet executeQuery(PreparedStatement stmt) {
        try {
            return stmt.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected int executeUpdate(PreparedStatement stmt) {
        try {
            return stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    protected void close() {
        try {
            this.conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
