package restData;

import Models.GameResult;
import Models.PlayerScore;

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


    public PlayerScore getGameResult(String user) {
        PlayerScore retrievedScore = null;
        try {
            sqlConnector.open();

            String statement = "SELECT sum(case when win then 0 else 1 end) as wins, sum(case when win then 1 else 0 end) as losses FROM Score s INNER JOIN user u ON u.id = s.userId WHERE u.username = \"" + user + "\"";

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
