package io.swagger.api.impl;

import io.swagger.api.util.TicTacToeStatusCode;
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
        TicTacToeStatusCode responseCode = TicTacToeStatusCode.OK;
        TicTacToeGame retrievedGm = null;
        Response responseObj = null;
        try {
            retrievedGm = tttDao.findGame(gameId);
            System.out.println("retrieved game" + retrievedGm.toString());
            System.out.println("next move" + nextMove.toString());
            if (retrievedGm.getPlayerToPlayNext().equalsIgnoreCase(nextMove.getPlayerName())) {
                retrievedGm.checkGameStatus();
                if (retrievedGm.getStatus() == TicTacToeGame.GameStatusEnum.InProgress.toString()) {
                    responseCode = retrievedGm.applyMove(nextMove);
                }
                if (responseCode == TicTacToeStatusCode.OK) {
                    tttDao.saveGame(retrievedGm);
                    System.out.println("game after move is applied" + retrievedGm.toString());
                }
            } else {
                responseCode = TicTacToeStatusCode.INVALID_MOVE;
            }
        } catch (Exception e) {
            //TODO log exception
            e.printStackTrace();
            responseCode = TicTacToeStatusCode.NOT_FOUND;
        }
        if (responseCode == TicTacToeStatusCode.OK) {
            responseObj = Response.ok().entity(new ApiResponseMessage(responseCode.getCode(), responseCode.getDesc() + retrievedGm.toString())).build();
        } else {
            responseObj = Response.serverError().entity(new ApiResponseMessage(responseCode.getCode(), responseCode.getDesc() + retrievedGm.toString())).build();
        }
        return responseObj;
    }

    @Override
    public Response getGameById(String gameId, SecurityContext securityContext) throws NotFoundException {
        TicTacToeStatusCode responseCode = TicTacToeStatusCode.OK;
        TicTacToeGame retrievedGm = null;
        Response responseObj = null;
        try {
            retrievedGm = tttDao.findGame(gameId);
        } catch (Exception e) {
            //TODO
            responseCode = TicTacToeStatusCode.NOT_FOUND;
        }
        if (responseCode == TicTacToeStatusCode.OK) {
            responseObj = Response.ok().entity(new ApiResponseMessage(responseCode.getCode(), responseCode.getDesc() + retrievedGm.toString())).build();
        } else {
            return Response.serverError().entity(new ApiResponseMessage(responseCode.getCode(), responseCode.getDesc())).build();
        }
        return responseObj;
    }

    @Override
    public Response joinGame(String gameId, Players players, SecurityContext securityContext) throws NotFoundException {
        TicTacToeStatusCode responseCode = TicTacToeStatusCode.OK;
        TicTacToeGame retrievedGm = tttDao.findGame(gameId);
        responseCode = retrievedGm.joinGame(players);
        Response responseObj = null;

        if (responseCode == TicTacToeStatusCode.OK) {
            responseObj = Response.ok().entity(new ApiResponseMessage(responseCode.getCode(), responseCode.getDesc() + retrievedGm.toString())).build();
        } else {
            return Response.serverError().entity(new ApiResponseMessage(responseCode.getCode(), responseCode.getDesc())).build();
        }
        return responseObj;
    }

    @Override
    public Response startNewGame(Players players, SecurityContext securityContext) throws NotFoundException {
        TicTacToeStatusCode responseCode = TicTacToeStatusCode.OK;
        TicTacToeGame newGm = null;
        Response responseObj = null;
        try {
            newGm = new TicTacToeGame(players);
            tttDao.saveGame(newGm);
        } catch (Exception e) {
            //TODO
            responseCode = TicTacToeStatusCode.UNKNOWN_SERVER_ERROR;
        }
        if (responseCode == TicTacToeStatusCode.OK) {
            responseObj = Response.ok().entity(new ApiResponseMessage(responseCode.getCode(), responseCode.getDesc() + newGm.toString())).build();
        } else {
            return Response.serverError().entity(new ApiResponseMessage(responseCode.getCode(), responseCode.getDesc())).build();
        }
        return responseObj;
    }
}
