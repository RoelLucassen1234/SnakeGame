package restData;

import Models.User;
import restInterface.IUserDal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDal implements IUserDal {

    private SQLConnector sqlConnector;

    public UserDal(){
        sqlConnector = new SQLConnector();
    }

    @Override
    public boolean create(User user) {
        try {

            sqlConnector.open();
            PreparedStatement statement = sqlConnector.getStatement("INSERT INTO user (username,password)" + " VALUES ('" + user.getUsername() + "','" + user.getPassword() + "')");
            sqlConnector.executeUpdate(statement);

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        } finally {
            sqlConnector.close();
        }
        return true;
    }

    @Override
    public User Login(User user) {
        User retrievedUser = null;
        try {
            sqlConnector.open();

            String statement = "Select * FROM user \n" +
                    " WHERE user.username = '" + user.getUsername() +"' AND user.password = '"+ user.getPassword() +"'";

            ResultSet rs = sqlConnector.executeQuery(sqlConnector.getStatement(statement));
          
            while (rs.next()) {
                retrievedUser = new User(rs.getString("username"),rs.getString("password"));
            }


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            sqlConnector.close();
            return retrievedUser;
        }
    }

    @Override
    public boolean getByUsername(String username) {
      String existUsername = null;
        try {
            sqlConnector.open();

            String statement = "Select * FROM User \n" +
                    " WHERE User.username = '" + username +"'";

            ResultSet rs = sqlConnector.executeQuery(sqlConnector.getStatement(statement));

            while (rs.next()) {
                existUsername = (rs.getString("username"));
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        } finally {

            sqlConnector.close();
            if (existUsername != null)
            return true;
            return false;
        }
    }
}
