

package com.xhm.rs.common.core;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice
 * 
 *  统一异常处理器
 *   拦截所有controller中间的异常，并抛出 return 
 */
@RestControllerAdvice
public class E {
	 


	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){		 
		return R.error("错误信息："+e.getMessage());
	}
	
	 
	 
}
