package controller;


import models.User;
import restlogica.UserLogic;
import restserver.RestResponseHelper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserController {
    private UserLogic userLogic = new UserLogic();

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {


        if (userLogic.register(user.getUsername(),user.getPassword())) {
            return Response.status(200).entity(RestResponseHelper.getSuccessResponse(true)).build();
        } else {
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(User user) {


        User newUser = userLogic.login(user.getUsername(), user.getPassword());
        if (newUser != null){
            return Response.status(200).entity(RestResponseHelper.getUser(newUser)).build();
        }else{
            return Response.status(400).entity(RestResponseHelper.getSuccessResponse(false)).build();
        }

    }
}
