package restInterface;

import Models.User;

public interface IUserDal {
    boolean create(User user);
    User Login(User user);
boolean getByUsername(String username);
}
