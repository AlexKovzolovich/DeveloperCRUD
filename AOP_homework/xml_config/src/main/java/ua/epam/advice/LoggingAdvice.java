package ua.epam.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void beforeCall(JoinPoint joinPoint) {
        logger.info("Entering " + joinPoint.getSignature().getName());
    }

    public void afterCall(JoinPoint joinPoint) {
        logger.info("Exiting " + joinPoint.getSignature().getName());
    }

    public void afterCallThrowing(JoinPoint joinPoint, Exception exception) {
        logger.info("Exiting " + joinPoint.getSignature().getName() + " with exception " + exception.toString());
    }
}
