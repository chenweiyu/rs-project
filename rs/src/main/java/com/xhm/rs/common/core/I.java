package com.xhm.rs.common.core;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xhm.rs.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 后端拦截
 * 自定义拦截器
 * HandlerInterceptor
 */
@Component
public class I implements HandlerInterceptor {

	@Autowired
	UserService userService;

	/**
	 * 方法之前拦截
	 * @throws IOException
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
       try{

         //1.-->get/post ajax 预先发送一次option请求，判断是否跨域	试探	
		if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
			System.out.println("OPTIONS请求，放行");
			return true;
		}
		//2.验证通过 
		String token = request.getHeader("authorization");  //获取前端token
        Long uid=J.verify(token);       //验证token
		if(uid!=null&&userService.getById(uid)!=null){
			
            





			return true;
		}
		  

	   }
	   catch(Exception e)
	   {
		  //异常写到前台，使用fastjson
		   response.getWriter().print(JSONObject.toJSONString(R.error(e.getMessage())));
	   }
		
	    

		return false; 		 
	}

}
