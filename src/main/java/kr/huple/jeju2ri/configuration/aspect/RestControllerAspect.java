package kr.huple.jeju2ri.configuration.aspect;

import kr.huple.jeju2ri.configuration.response.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RestControllerAspect {

    @Around("execution(* kr.huple.jeju2ri.api.controller.*.*(..))")
    public RestResponse<Object> restResponseHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        return new RestResponse<>(HttpStatus.OK.value(), "success", joinPoint.proceed());
    }

}
