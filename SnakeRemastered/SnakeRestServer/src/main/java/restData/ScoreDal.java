package restData;

import Models.GameResult;
import Models.PlayerScore;
import Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreDal {
    private SQLConnector sqlConnector;
    private final static Logger LOGGER = Logger.getLogger(ScoreDal.class.getName());

    public ScoreDal(){
        sqlConnector = new SQLConnector();
    }

    public boolean create(GameResult user) {
        try {

            //TODO MAAK DE STATEMENT CORRECT!!!!!
            sqlConnector.open();
            PreparedStatement statement = sqlConnector.getStatement("INSERT INTO score (userId,win)" + " VALUES ('" + user.getUsername() + "','" + user.isWin() + "')");
            sqlConnector.executeUpdate(statement);

        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            return false;

        } finally {
            sqlConnector.close();
        }
        return true;
    }


    public PlayerScore getGameResult(User user) {
        PlayerScore retrievedScore = null;
        try {
            sqlConnector.open();

            String statement = "Select * FROM user \n" +
                    " WHERE user.username = '" + user.getUsername() +"' AND user.password = '"+ user.getPassword() +"'";

            ResultSet rs = sqlConnector.executeQuery(sqlConnector.getStatement(statement));

            while (rs.next()) {
                retrievedScore = new PlayerScore(rs.getInt("wins"),rs.getInt("losses"));
            }


        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());

        } finally {

            sqlConnector.close();
        }
        return retrievedScore ;
    }
}
