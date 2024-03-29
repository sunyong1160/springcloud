package com.example.resolver;

import com.alibaba.fastjson.JSONObject;
import com.example.annonation.CurrentUser;
import com.example.domain.UserBean;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Objects;


/**
 * 自定义解析器
 */
@Component
public class MyCurrentUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean flag = methodParameter.hasParameterAnnotation(CurrentUser.class);
        System.out.println(methodParameter.getParameterType().isAssignableFrom(UserBean.class));
        System.out.println(methodParameter.getMethod().getName());
        return flag;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 从session中获取用户信息
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // 后续可以做加解密处理
        String header = request.getHeader("enen");
        if("1".equals(header)){
            return new UserBean(2l, "lisi");
        }
        String body = new String(IOUtils.toByteArray(Objects.requireNonNull(request).getInputStream()));
        UserBean userBean = JSONObject.parseObject(body, UserBean.class);
        //处理业务逻辑操作
        userBean.setUsername(new String(Base64.getEncoder().encode(userBean.getUsername().getBytes())));
        return userBean;
    }
}
