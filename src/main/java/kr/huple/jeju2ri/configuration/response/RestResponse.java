package kr.huple.jeju2ri.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestResponse<T> {

    private int code;
    private String message;
    private T result;

}
