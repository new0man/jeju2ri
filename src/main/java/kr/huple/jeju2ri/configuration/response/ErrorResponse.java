package kr.huple.jeju2ri.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private Integer code;
    private String message;

}
