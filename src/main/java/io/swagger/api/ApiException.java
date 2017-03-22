package io.swagger.api;

@javax.annotation.Generated(value = "io.io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-03-21T18:30:20.420Z")
public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
