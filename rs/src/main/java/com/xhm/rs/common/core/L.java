package com.xhm.rs.common.core;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 使用AOP处理log
 * 
 * 记录controller层方法运行过程
 * 
 * @Component：spring托管类
 * @Aspect:切面类
 * 
 */
@Aspect
@Component
public class L {

	/**
	 * 定义切入点
	 * 
	 * controller包及子包下的所有方法
	 */
	@Pointcut("execution(* com.jd.after.modules.*.controller..*.*(..))")
	public void pointCut() {
	}

	/**
	 * 记录日志，保留找一个即可
	 * 
	 * RequestLog{url='http://127.0.0.1:8080/aop/asdfafd', ip='127.0.0.1',
	 * classMethod='com.neu.t9.controller.AopController.testAop', args=[asdfafd],
	 * result=Hello asdfafd}
	 * 
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(pointcut = "pointCut()", returning = "result")
	public void doAfterReturning(JoinPoint joinPoint, Object result) {

		// 获取请求的url和ip
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL().toString();
		String ip = request.getRemoteAddr();

		// 获取执行的方法和参数
		Signature signature = joinPoint.getSignature();
		String classMethod = signature.getDeclaringTypeName() + "." + signature.getName();
		Object[] args = joinPoint.getArgs();

		String log = "Controller请求日志------：{" + "url='" + url + '\'' + ", ip='" + ip + '\'' + ", classMethod='" + classMethod
				+ '\'' + ", args=" + Arrays.toString(args) + ", result=" + result + ", date=" + new Date() + '}';

		System.out.println(log);

	}

}
