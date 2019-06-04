package restServer;

import Models.PlayerScore;
import Models.SnakeRestResponse;
import Models.User;
import com.google.gson.Gson;
import controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestResponseHelper {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final Gson gson = new Gson();
    private static final String serverResponse = "[Server response] {}";

    public static String getSuccessResponse(boolean success)
    {
        SnakeRestResponse response = new SnakeRestResponse();
        response.setSuccess(success);
        String output = gson.toJson(response);
        log.info(serverResponse, output);
        return output;
    }

    public static String getErrorResponseString()
    {
        SnakeRestResponse response = new SnakeRestResponse();
        response.setSuccess(false);
        String output = gson.toJson(response);
        log.info(serverResponse, output);
        return output;
    }

    public static String getUser(User user){
        SnakeRestResponse response = new SnakeRestResponse();
        response.setSuccess(true);
        response.setUser(user);
        String output = gson.toJson(response);
        log.info(serverResponse, output);
        return output;
    }
    public static String getPlayerScore(PlayerScore playerScore){
        SnakeRestResponse response = new SnakeRestResponse();
        response.setPlayerScore(playerScore);
        String output = gson.toJson(response);
        log.info(serverResponse, output);
        return output;
    }


}
