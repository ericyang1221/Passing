package com.passing.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class LogUtil {
	
	public static Logger log = Logger.getLogger(LogUtil.class);

	public void beforeMethodLog(JoinPoint jp) {
		Object[] para = jp.getArgs();
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		
		log.info("======================================" + className + "::" + methodName + " START=======================================");
		if (para.length != 0) {
			for (int i = 0; i < para.length; i++) {
				log.info("PARAMETER[" + i +"] : " + para[i]);
			}
		}
	}
	
	public void afterMethodLog(JoinPoint jp) {
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		
		log.info("======================================" + className + "::" + methodName + " END=======================================");
	}
	
}
