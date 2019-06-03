package loginClient;

import Models.SnakeRestResponse;
import Models.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class SnakeLoginClient {
    private final Logger log = LoggerFactory.getLogger(SnakeLoginClient.class);

    private static final String url = "http://localhost:8090/user";

    private final Gson gson = new Gson();

    private static SnakeLoginClient instance;

    public SnakeLoginClient() {

    }

    public static SnakeLoginClient getInstance() {
        if(instance == null){
            instance = new SnakeLoginClient();
        }
        return instance;
    }
    public SnakeRestResponse executeQueryPost(User userRequest, String queryPost) {

        // Build the query for the REST service
        final String query = url + queryPost;
        log.info("[Query Post] : {}", query);

        // Execute the HTTP POST request
        HttpPost request = new HttpPost(query);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept", "application/json");
        StringEntity params;
        params = new StringEntity(gson.toJson(userRequest), ContentType.APPLICATION_JSON);
        request.setEntity(params);
        return executeHttpUriRequest(request);
    }
    public SnakeRestResponse executeQueryGet(String queryGet) {

        // Build the query for the REST service
        final String query = url + queryGet;
        log.info("[Query Get] : {}", query);

        // Execute the HTTP GET request
        HttpGet httpGet = new HttpGet(query);
        return executeHttpUriRequest(httpGet);
    }


    public User login(String username, String password) {
        User userRequest = new User(username, password);
        String queryPost = "/login";
        SnakeRestResponse response = executeQueryPost(userRequest, queryPost);
        return response.getUser();
    }

    public User register(String username, String password) {
        User userRequest = new User(username, password);
        String queryPost = "/register";
        SnakeRestResponse response = executeQueryPost(userRequest, queryPost);
        return response.getUser();
    }


    private SnakeRestResponse executeHttpUriRequest(HttpUriRequest httpUriRequest) {

        // Execute the HttpUriRequest
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
            log.info("[Status Line] : {}", response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            log.info("[Entity] : {}", entityString);
            return gson.fromJson(entityString, SnakeRestResponse.class);
        } catch (IOException e) {
            log.info("IOException : {}", e);
            SnakeRestResponse restResponse = new SnakeRestResponse();
            restResponse.setSuccess(false);
            return restResponse;
        } catch (JsonSyntaxException e) {
            log.info("JsonSyntaxException : {}", e);
            SnakeRestResponse restResponse = new SnakeRestResponse();
            restResponse.setSuccess(false);
            return restResponse;
        }
    }
}
