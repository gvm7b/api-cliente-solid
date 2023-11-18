package br.com.senac.api.frameWork.annotions;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LogRestAspect {

    private Logger log = Logger.getLogger(LogRestAspect.class.getName());
    @Around("@annotation(LogRest)")
    public Object logRequisicao(ProceedingJoinPoint joinPoint) throws Throwable {
        Object procedimento = joinPoint.proceed();

        log.info("Metodo: " + joinPoint.getSignature());
        log.info("Request: " + (joinPoint.getArgs().length > 0 ? joinPoint.getArgs()[0].toString() : null));
        log.info("Response: " + procedimento.toString());

        return procedimento;
    }
}
