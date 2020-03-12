package ua.epam.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(ua.epam.annotation.Logging)")
    public void pointcut() {}

    @Before("pointcut()")
    public void beforeCall(JoinPoint joinPoint) {
        logger.info("Entering " + joinPoint.getSignature().getName());
    }

    @After("pointcut()")
    public void afterCall(JoinPoint joinPoint) {
        logger.info("Exiting " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public void afterCallThrowing(JoinPoint joinPoint, Exception exception) {
        logger.info("Exiting " + joinPoint.getSignature().getName() + " with exception " + exception.toString());
    }
}
