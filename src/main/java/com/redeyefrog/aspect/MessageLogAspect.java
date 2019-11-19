package com.redeyefrog.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MessageLogAspect {

    @Around(value = "execution(public * com.redeyefrog.handler.*.*(..))")
    public Object doAround(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();

        Object obj = null;
        try {
            log.info("<<start>> class: {}, method: {}, args: {}", signature.getDeclaringTypeName(), signature.getName(), point.getArgs());

            obj = point.proceed();
        } catch (Throwable t) {
            log.error("<<error>> class: {}, method: {}, throw: {}", signature.getDeclaringTypeName(), signature.getName(), t.getMessage(), t);

        } finally {
            log.info("<<end>> class: {}, method: {}, obj: {}", signature.getDeclaringTypeName(), signature.getName(), obj);

        }

        return obj;
    }

}
