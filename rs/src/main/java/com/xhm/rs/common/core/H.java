package com.xhm.rs.common.core;

import com.xhm.rs.entity.User;
import com.xhm.rs.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 
 * 自定义解析器 解析当前用户注解
 * 
 * @U
 */
@Component
public class H implements HandlerMethodArgumentResolver {

    @Autowired
    UserService userService;

    /**
     * 判定当前方法上的注解是@U，并且返回类型是User,true交给resolveArgument，否则不处理
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(U.class) && parameter.getParameterType().isAssignableFrom(User.class);
    }

    /**
     * 获取当前用户
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest request,
            WebDataBinderFactory factory) throws Exception {
        // 获取用户token
        String token = request.getHeader("authorization");  //获取前端token
        Long userId=J.verify(token);       //验证token
        User user = userService.getById(userId);
        return user;
    }

}
