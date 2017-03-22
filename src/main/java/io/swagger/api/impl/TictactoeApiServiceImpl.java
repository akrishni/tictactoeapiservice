package io.swagger.api.impl;

import io.swagger.model.Move;
import io.swagger.model.Players;
import io.swagger.model.TicTacToeGame;

import io.swagger.api.NotFoundException;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.TictactoeApiService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-03-21T18:30:20.420Z")
public class TictactoeApiServiceImpl extends TictactoeApiService {
    TicTacToeDAO tttDao = new TicTacToeDAO();
    @Override
    public Response applyNextMove(String gameId, Move nextMove, SecurityContext securityContext) throws NotFoundException {
        int responseMessage = ApiResponseMessage.OK;
        TicTacToeGame retrievedGm = null;
        Response responseObj = null;
        try {
            retrievedGm = tttDao.findGame(gameId);
            System.out.println("retrieved game" + retrievedGm.toString());
            System.out.println("next move" + nextMove.toString());
            if(retrievedGm.getPlayerToPlayNext().equalsIgnoreCase(nextMove.getPlayerName())) {
                if (retrievedGm.checkGameStatus() && retrievedGm.getStatus() == TicTacToeGame.GameStatusEnum.InProgress.toString()) {
                    responseMessage = retrievedGm.applyMove(nextMove);
                }
                if (responseMessage == 200) {
                    tttDao.saveGame(retrievedGm);
                    System.out.println("game after move is applied" + retrievedGm.toString());
                } else {
                    System.out.println("move could not be applied response code ::" + responseMessage);
                }
            }else {
                System.out.println("next player to play is diff from the player sending the move");
                responseMessage = 498;
            }
        }catch (Exception e){
            //TODO log exception
            e.printStackTrace();
            responseMessage = 404;
        }
        if (responseMessage == ApiResponseMessage.OK){
            responseObj = Response.ok().entity(new ApiResponseMessage(responseMessage, retrievedGm.toString())).build();
        } else {
            responseObj = Response.serverError().entity(new ApiResponseMessage(responseMessage, retrievedGm.toString())).build();
        }
        return responseObj;
        //return Response.ok().entity(new ApiResponseMessage(responseMessage, retrievedGm.toString())).build();
    }
    @Override
    public Response getGameById(String gameId, SecurityContext securityContext) throws NotFoundException {
        TicTacToeGame retrievedGm = tttDao.findGame(gameId);
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, retrievedGm.toString())).build();
    }
    @Override
    public Response joinGame(String gameId, Players players, SecurityContext securityContext) throws NotFoundException {
        int responseMessage = ApiResponseMessage.OK;
        TicTacToeGame retrievedGm = tttDao.findGame(gameId);
        //if (retrievedGm.checkCanGameBeJoined()){
        //    responseMessage = retrievedGm.joinGame(players);
        //}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response startNewGame(Players players, SecurityContext securityContext) throws NotFoundException {
        TicTacToeGame gm = new TicTacToeGame(players);
        tttDao.saveGame(gm);
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, gm.toString())).build();
    }
}
