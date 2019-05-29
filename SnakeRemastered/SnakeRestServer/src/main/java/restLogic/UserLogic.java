package restLogic;


import restData.UserDal;
import restInterface.IUserDal;
import restModel.User;

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
        if (iUserDal.getByUsername(username)){
            return false;
        }

        User user = new User(username, password);
        return this.iUserDal.create(user);
    }


    public boolean login(String username, String password) {

        if (username != null && password != null) {
            if (iUserDal.Login(new User(username, password)) != null)
                return true;
        }
        return false;
        }

    }

