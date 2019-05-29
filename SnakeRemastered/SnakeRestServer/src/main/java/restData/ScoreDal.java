package restData;

import restModel.GameResult;
import restModel.PlayerScore;
import restModel.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScoreDal {
    private SQLConnector sqlConnector;

    public ScoreDal(){
        sqlConnector = new SQLConnector();
    }

    public boolean create(GameResult user) {
        try {

            sqlConnector.open();
            PreparedStatement statement = sqlConnector.getStatement("INSERT INTO score (userId,win)" + " VALUES ('" + user.getPlayerNr() + "','" + user.isWin() + "')");
            sqlConnector.executeUpdate(statement);

        } catch (Exception ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();

        } finally {

            sqlConnector.close();
            return retrievedScore ;
        }
    }
}
