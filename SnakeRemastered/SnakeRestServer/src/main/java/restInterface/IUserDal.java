package restInterface;

import restModel.User;

public interface IUserDal {
    boolean create(User user);
    User Login(User user);
boolean getByUsername(String username);
}
