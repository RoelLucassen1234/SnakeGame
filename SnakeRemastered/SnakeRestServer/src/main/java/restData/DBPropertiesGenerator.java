package restData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBPropertiesGenerator {

    private static FileOutputStream output;
    private static final Logger LOG = Logger.getLogger(DBPropertiesGenerator.class.getName());

    public static void main(String[] args) {


        try {
            Properties prop = new Properties();
            output = new FileOutputStream("db.prop");

            // set the properties value
            prop.setProperty("jdbc.drivers", "org.sqlite.JDBC");
            prop.setProperty("jdbc.url", "jdbc:sqlite:databaseSnake.db");
            prop.setProperty("jdbc.username", "");
            prop.setProperty("jdbc.password", "");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            LOG.warning(io.getMessage());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    LOG.warning(e.getMessage());
                }
            }

        }
    }
}

