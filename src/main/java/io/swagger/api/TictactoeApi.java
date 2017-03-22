package io.swagger.api;

import io.swagger.api.factories.TictactoeApiServiceFactory;

import io.swagger.annotations.ApiParam;

import io.swagger.model.Move;
import io.swagger.model.Players;
import io.swagger.model.TicTacToeGame;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/tictactoe")


@io.swagger.annotations.Api(description = "the tictactoe API")
@javax.annotation.Generated(value = "io.io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-03-21T18:30:20.420Z")
public class TictactoeApi  {
   private final TictactoeApiService delegate = TictactoeApiServiceFactory.getTictactoeApi();

    @PUT
    @Path("/{gameId}/move")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "send the next move by the player or if playing with computer ask the computer to make the next move", notes = "sends next move or ask computer to make the next move", response = TicTacToeGame.class, tags={ "new move", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = TicTacToeGame.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid input", response = TicTacToeGame.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Game for which the move was sent is not found", response = TicTacToeGame.class),
        
        @io.swagger.annotations.ApiResponse(code = 498, message = "Invalid move - Not player turn", response = TicTacToeGame.class),
        
        @io.swagger.annotations.ApiResponse(code = 499, message = "Invalid move - Unkown position or position already taken", response = TicTacToeGame.class) })
    public Response applyNextMove(@ApiParam(value = "ID of game that needs to be fetched",required=true) @PathParam("gameId") String gameId
,@ApiParam(value = "next move" ) Move nextMove
,@Context SecurityContext securityContext)
    throws io.swagger.api.NotFoundException {
        return delegate.applyNextMove(gameId,nextMove,securityContext);
    }
    @GET
    @Path("/{gameId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find the TicTackToe game by ID", notes = "Returns status of the TicTacToe game by ID", response = TicTacToeGame.class, tags={ "existing game", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = TicTacToeGame.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Game not found", response = TicTacToeGame.class) })
    public Response getGameById(@ApiParam(value = "ID of game that needs to be fetched",required=true) @PathParam("gameId") String gameId
,@Context SecurityContext securityContext)
    throws io.swagger.api.NotFoundException {
        return delegate.getGameById(gameId,securityContext);
    }
    @PUT
    @Path("/{gameId}/join")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "join an existing game", notes = "join", response = TicTacToeGame.class, tags={ "join game", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = TicTacToeGame.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Game for which the move was sent is not found", response = TicTacToeGame.class),
        
        @io.swagger.annotations.ApiResponse(code = 422, message = "PlayerId already in the game", response = TicTacToeGame.class) })
    public Response joinGame(@ApiParam(value = "ID of game that the player wants to join",required=true) @PathParam("gameId") String gameId
,@ApiParam(value = "players to play" ,required=true) Players players
,@Context SecurityContext securityContext)
    throws io.swagger.api.NotFoundException {
        return delegate.joinGame(gameId,players,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Starts a new game of tictactoe", notes = "starts a new game of tictacktoe and returns the game object in the response", response = TicTacToeGame.class, tags={ "new game", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = TicTacToeGame.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid input", response = TicTacToeGame.class) })
    public Response startNewGame(@ApiParam(value = "player to play" ,required=true) Players players
,@Context SecurityContext securityContext)
    throws io.swagger.api.NotFoundException {
        return delegate.startNewGame(players,securityContext);
    }
}
