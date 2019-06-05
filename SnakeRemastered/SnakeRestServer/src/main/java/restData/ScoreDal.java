package restData;

import Models.GameResult;
import Models.PlayerScore;
import restInterface.IScoreDal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreDal implements IScoreDal {
    private SQLConnector sqlConnector;
    private final static Logger LOGGER = Logger.getLogger(ScoreDal.class.getName());

    public ScoreDal(){
        sqlConnector = new SQLConnector();
    }

    public boolean create(GameResult user) {
        try {


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

            String statement = "select\n" +
                    "    select sum(win = 1) as wins, sum(win = 0) as losses\n" +
                    ", u.username as username FROM Score s INNER JOIN user u ON u.id = s.userId\n" +
                    "   WHERE u.username = \"" + user + "\"";

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

    public List<PlayerScore> getAllScoresFromUsers(){
        List<PlayerScore> retrievedScores = new ArrayList<>();
        try {
            sqlConnector.open();

            String statement = "select sum(win = 1) as wins, sum(win = 0) as losses\n" +
                    ", u.username as username FROM Score s INNER JOIN user u ON u.id = s.userId\n" +
                    "  ";

            ResultSet rs = sqlConnector.executeQuery(sqlConnector.getStatement(statement));

            while (rs.next()) {
               PlayerScore player = new PlayerScore(rs.getInt("wins"),rs.getInt("losses"), rs.getString("username"));
               retrievedScores.add(player);
            }


        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());

        } finally {

            sqlConnector.close();
        }
        return retrievedScores ;
    }
}
