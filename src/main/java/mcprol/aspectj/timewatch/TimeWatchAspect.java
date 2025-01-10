package mcprol.aspectj.timewatch;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * aspect for @TimeWatch annotation
 * @author mcprol
 */
@Aspect
public class TimeWatchAspect {
    private static final Logger logger = LoggerFactory.getLogger(TimeWatchAspect.class);

    @Pointcut("@annotation(TimeWatch)")
    public void runTimeWatch() {}

    @Around("runTimeWatch()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        
        Instant start = Instant.now();
        Object retValue = pjp.proceed();
        Instant end = Instant.now();
        
        long timeElapsed = Duration.between(start, end).toMillis();
        logger.info("TimeWatch: {} at {} takes {} ms BATATA  ", pjp.toShortString(), pjp.getSourceLocation(), timeElapsed);
        
        return retValue;
    }
}
