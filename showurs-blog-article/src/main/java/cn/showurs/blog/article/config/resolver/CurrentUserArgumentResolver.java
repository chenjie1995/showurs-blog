package cn.showurs.blog.article.config.resolver;

import cn.showurs.blog.article.client.UserClient;
import cn.showurs.blog.common.annotation.CurrentUser;
import cn.showurs.blog.common.constant.RequestInfo;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.exception.UnauthorizedException;
import cn.showurs.blog.common.vo.user.User;
import cn.showurs.blog.common.vo.user.UserJwtSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by CJ on 2019/3/13 16:07.
 */
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    private static final Logger logger = LoggerFactory.getLogger(CurrentUserArgumentResolver.class);

    @Resource
    private UserClient userClient;

    @Override
    public boolean supportsParameter(@NonNull MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, @NonNull NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        if (request == null) {
            throw new BusinessException("request为空");
        }

        UserJwtSubject userJwtSubject = (UserJwtSubject) request.getAttribute(RequestInfo.ATTRIBUTE_USER_INFO_NAME);

        if (userJwtSubject == null) {
            throw new UnauthorizedException("无Token");
        }

        if (Long.class.isAssignableFrom(methodParameter.getParameterType())) {
            return userJwtSubject.getUserId();
        }

        if (User.class.isAssignableFrom(methodParameter.getParameterType())) {
            return userClient.getById(userJwtSubject.getUserId()).getData();
        }

        return null;
    }
}
