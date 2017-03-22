package io.swagger.api.factories;

import io.swagger.api.TictactoeApiService;
import io.swagger.api.impl.TictactoeApiServiceImpl;

@javax.annotation.Generated(value = "io.io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-03-21T18:30:20.420Z")
public class TictactoeApiServiceFactory {
    private final static TictactoeApiService service = new TictactoeApiServiceImpl();

    public static TictactoeApiService getTictactoeApi() {
        return service;
    }
}
