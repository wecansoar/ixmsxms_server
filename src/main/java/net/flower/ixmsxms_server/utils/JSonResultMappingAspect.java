package net.flower.ixmsxms_server.utils;

import net.flower.ixmsxms_server.domain.JSonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class JSonResultMappingAspect {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Around("@annotation(net.flower.ixmsxms_server.utils.JSonResultMapping)")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		JSonResult result = new JSonResult();
		
		try {
			result.setData(joinPoint.proceed());
		} catch (Throwable e) {
			result.setMessage(e.getMessage());
			logger.error("error", e);
		}
		
		return result;
	}
}
