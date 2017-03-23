package io.swagger.api.util;

/**
 * Created by akrishniyer on 3/22/17.
 */
public enum TicTacToeStatusCode {

    OK(200, "OK/Successful "),
    CREATED(201, "Game Created "),
    BAD_REQUEST(400, "Bad/Invalid Request "),
    UNAUTHORIZED(401, "Unauthorized "),
    NOT_FOUND(404, "Game Not Found "),
    INVALID_MOVE(498, "Invalid Move. Not Player Turn "),
    INVALID_POSITION(499, "Invalid Move. Unknown Position or Position Already Taken "),
    UNKNOWN_MOVE_ERROR(497, "Unknown Error In Move "),
    UNKNOWN_SERVER_ERROR(496, "Unknown Error In Game Creation "),
    INVALID_JOIN(495, "Can Only Join A Game In State - Waiting For Player " ),
    INVALID_NUMBER_PLAYERS(494, "Number Of Players Has To Be 1 " )
    ;

    private int code;
    private String desc;
    private String text;

    TicTacToeStatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
        this.text = Integer.toString(code);
    }

    /**
     * Gets the status code
     * @return the status code number
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the status code as a text string
     * @return the status code as a text string
     */
    public String asText() {
        return text;
    }

    /**
     * Get the description
     * @return the description of the status code
     */
    public String getDesc() {
        return desc;
    }

}
