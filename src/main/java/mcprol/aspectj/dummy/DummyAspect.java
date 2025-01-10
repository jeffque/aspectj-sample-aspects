package mcprol.aspectj.dummy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * sample aspect
 * @author mcprol
 */
@Aspect
public class DummyAspect {
    private static final Logger logger = LoggerFactory.getLogger(DummyAspect.class);

    @Pointcut("execution(int mcprol.aspectj.dummy.DummyCounter.add(int))") 
    public void callToAddMethod() {}

    @Pointcut("within(@DummyAnnotationClass *) && execution(* *(..))") 
    public void callToRandomMethod() {}

    @Around("callToRandomMethod()")
    public Object aroundRandom(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("hey!!!");
        DummyCounter counter = (DummyCounter)pjp.getTarget();

        if (pjp.getSignature() instanceof MethodSignature sig) {
            System.out.println(sig);
            final var method = sig.getMethod();
            final var annotations = method.getParameterAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                final var annotation = annotations[i];
                if (annotation.length > 0) {
                    for (final var varAnnotation: annotation) {
                        if (varAnnotation instanceof DummyAnnotation) {
                            int dummyValue = (int) pjp.getArgs()[i];
                            System.out.println("dummy value: " + dummyValue);
                        }
                    }
                }
            }
        }
        dumpJoinPoint(pjp);
        
        return pjp.proceed(pjp.getArgs());
    }

    @Before("callToAddMethod()")
    public void before(JoinPoint jp) throws Throwable {
        DummyCounter counter = (DummyCounter)jp.getTarget();
        int inc = (int) jp.getArgs()[0];
        
        logger.info("Before DummyCounter.add() with values DummyCounter={}, inc={}, value={}", counter, inc, counter.value);
        dumpJoinPoint(jp);
    }
    
    @Around("callToAddMethod()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        DummyCounter counter = (DummyCounter)pjp.getTarget();
        int inc = (int) pjp.getArgs()[0];
        
        logger.info("Running DummyCounter.add() with values DummyCounter={}, inc={}, value={}", counter, inc, counter.value);

        if (pjp.getSignature() instanceof MethodSignature sig) {
            System.out.println(sig);
            final var method = sig.getMethod();
            var annotations = method.getParameterAnnotations();
            System.out.println(annotations.length);
        }
        // final var sig = (MethodSignature) pjp.getSignature();
        // System.out.println(sig);
        dumpJoinPoint(pjp);
        
        Object returnValue = pjp.proceed(pjp.getArgs());
        
        if (counter.value > DummyCounter.LIMIT) {
            counter.value = DummyCounter.LIMIT;
            returnValue = counter.value;
        }
        
        return returnValue;
    }

    @After("callToAddMethod()")
    public void after(JoinPoint jp) throws Throwable {
        DummyCounter counter = (DummyCounter)jp.getTarget();
        int inc = (int) jp.getArgs()[0];
        
        logger.info("After running DummyCounter.add() with values DummyCounter={}, inc={}, value={}", counter, inc, counter.value);
        dumpJoinPoint(jp);
    }
    

    private void dumpJoinPoint(JoinPoint jp) {
        logger.info("   jp: joinpoint {} at {}", jp.toShortString(), jp.getSourceLocation());
        logger.info("   jp: this: {}", jp.getThis());
        logger.info("   jp: target: {}", jp.getTarget());
        logger.info("   jp: args: {}", jp.getArgs());
        logger.info("   jp: kind: {}", jp.getKind());
    }
}
