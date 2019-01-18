/**
 * 
 */
package es.cgarcia.decocina.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Miguel Ángel Álvarez García
 *
 */
@Aspect
public class Profiler {

	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(Profiler.class);
	
	/**
	 * Pointcut.
	 */
    @Pointcut("execution(* es.cgarcia.decocina.web.service.*.*(..))")
    public void profiler() {
    }

    /**
     * Calcula el tiempo de ejecucion del método.
     * @param pjp ProceedingJoinPoint.
     * @return Object.
     * @throws Throwable
     */
    @Around("profiler()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
    	
        Long start = System.currentTimeMillis();
        
        Object output = pjp.proceed();
        
        Long tiempoEjecucion = (System.currentTimeMillis() - start);
        
        Signature  signature = pjp.getSignature();
        String metodo = signature.getName();
        
        logger.info("Método {} ejecutado en {} ms. ", metodo, tiempoEjecucion);
        
        return output;
    }
}
