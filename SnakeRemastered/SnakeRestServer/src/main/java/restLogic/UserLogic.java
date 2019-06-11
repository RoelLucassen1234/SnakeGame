package restLogic;


import models.User;
import restData.UserDal;
import restInterface.IUserDal;

public class UserLogic {

    private IUserDal iUserDal;

    public UserLogic() {
        iUserDal = new UserDal();
    }


    public boolean register(String username, String password) {
        if (username.equals("") || password.equals("")) {
            return false;
        }
        if (password.length() < 8) {
            return false;
        }
        if (iUserDal.getByUsername(username)) {
            return false;
        }

        User user = new User(username, password);
        return this.iUserDal.create(user);
    }


    public User login(String username, String password) {

        if (username != null && password != null) {
            return iUserDal.Login(new User(username, password));
        }
        return null;
    }
}

