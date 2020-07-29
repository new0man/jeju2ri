package kr.huple.jeju2ri.configuration.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceExceptionAspect {

    @Around("execution(* kr.huple.jeju2ri.api.service.*.*(..))")
    public Object serviceExceptionHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            // 서비스 로직 에러를 RuntimeException으로 컨트롤러에 전달.
            throw new RuntimeException(e);
        }
    }

}
