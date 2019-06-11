package Interface;

import models.User;

public interface IRESTRequests {
    User login(String username, String password);
    boolean register(String username, String password);

}
