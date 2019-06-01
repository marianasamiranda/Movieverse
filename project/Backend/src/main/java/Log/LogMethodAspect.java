package Log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMethodAspect {

    private static final Logger logger = LogManager.getLogger(LogMethodAspect.class);

    @Before("@annotation(LogMethod)")
    public void logMethod(JoinPoint joinPoint) {
        logger.info(((MethodSignature) joinPoint.getSignature()).getMethod());
    }
}