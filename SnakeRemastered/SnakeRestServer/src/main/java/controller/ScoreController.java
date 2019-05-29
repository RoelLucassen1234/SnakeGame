package controller;

import restLogic.ScoreLogic;
import restModel.GameResult;
import restModel.PlayerScore;
import restModel.User;
import restServer.RestResponseHelper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("score")
public class ScoreController {

    ScoreLogic scoreLogic = new ScoreLogic();

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBatleResult(GameResult user) {

        System.out.println("[Server postUser]");
        if (scoreLogic.addbattleResult(user)) {
            return Response.status(200).entity(RestResponseHelper.getSuccessResponse(true)).build();
        } else {
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
    }

    @GET
    @Path("/user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBattleResult(User user) {

        System.out.println("[Server getUser]");
        PlayerScore playerScore = scoreLogic.getScoreboard(user);
        if (playerScore != null) {
            return Response.status(200).entity(RestResponseHelper.getPlayerScore(playerScore)).build();
        } else {
            return Response.status(200).entity(RestResponseHelper.getSuccessResponse(false)).build();
        }
    }
}
