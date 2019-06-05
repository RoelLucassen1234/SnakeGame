package controller;

import Models.GameResult;
import Models.PlayerScore;
import restLogic.ScoreLogic;
import restServer.RestResponseHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/score")
public class ScoreController {

    ScoreLogic scoreLogic = new ScoreLogic();

    @POST
    @Path("/addResult")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGameResult(GameResult user) {

        System.out.println("[Server postUser]");
        if (scoreLogic.addbattleResult(user)) {
            return Response.status(200).entity(RestResponseHelper.getSuccessResponse(true)).build();
        } else {
            return Response.status(400).entity(RestResponseHelper.getSuccessResponse(false)).build();
        }
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBattleResult(@PathParam("username") String username) {

        System.out.println("[Server getUser]");
        PlayerScore playerScore = scoreLogic.getScoreboardOfUser(username);
        if (playerScore != null) {
            return Response.status(200).entity(RestResponseHelper.getPlayerScore(playerScore)).build();
        } else {
            playerScore = new PlayerScore(0,0);
            return Response.status(200).entity(RestResponseHelper.getPlayerScore(playerScore)).build();
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBattleResult() {

        System.out.println("[Server getUsers]");
        List<PlayerScore> playerScores = scoreLogic.getScoreboard();
        if (playerScores != null) {
            return Response.status(200).entity(RestResponseHelper.getPlayerScores(playerScores)).build();
        } else {
            return Response.status(200).entity(RestResponseHelper.getSuccessResponse(false)).build();
        }
    }
}
