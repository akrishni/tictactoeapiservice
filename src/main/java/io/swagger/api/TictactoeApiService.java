package io.swagger.api;

import io.swagger.model.Move;
import io.swagger.model.Players;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-03-21T18:30:20.420Z")
public abstract class TictactoeApiService {
    public abstract Response applyNextMove(String gameId,Move nextMove,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getGameById(String gameId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response joinGame(String gameId,Players players,SecurityContext securityContext) throws NotFoundException;
    public abstract Response startNewGame(Players players,SecurityContext securityContext) throws NotFoundException;
}
