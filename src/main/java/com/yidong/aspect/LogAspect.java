package com.yidong.aspect;

//import java.util.Arrays;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.google.gson.Gson;
//
//import lombok.extern.slf4j.Slf4j;

//@Component
//@Aspect
//@Slf4j
public class LogAspect {

//	private Gson gson = new Gson();
//	//@Pointcut("execution(public * com.yidong.controller..*.*(..))")
//	public void aspectPoint() {
//	}
//
//	//@Before("aspectPoint()")
//	public void LogRequestInfo(JoinPoint joinPoint) {
//		try {
//			// 执行controller 方法之前需要记录的请求信息
//			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//			HttpServletRequest request = attributes.getRequest();
//
//			//log.info("URL: {}",request.getRequestURL());
//			//log.info("Http_Method: {} ", request.getMethod());
//			//log.info("IP: {}", request.getRemoteAddr());
//			//log.info("Class:{}",joinPoint.getClass());
//			//log.info("Static:{}",joinPoint.getStaticPart());
//			//log.info("Target:{}",joinPoint.getTarget().getClass());
//			//log.info("Signature:{}",joinPoint.getSignature());
//			//log.info("Class_Method: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//			//log.info("Args: {}", Arrays.toString(joinPoint.getArgs()));
//		} catch (Exception e) {
//			 e.printStackTrace();
//		}
//	}
//
//	//在方法执行完结后打印返回内容
//	//@AfterReturning(returning = "o",pointcut = "execution(public * com.yidong.controller..*.*(..))")
//	public void methodAfterReturing(Object o ){
//		try {
//		//	log.info("Response:"+gson.toJson(o));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}