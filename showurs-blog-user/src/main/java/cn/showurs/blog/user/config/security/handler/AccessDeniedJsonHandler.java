package cn.showurs.blog.user.config.security.handler;

import cn.showurs.blog.common.exception.UnauthorizedException;
import cn.showurs.blog.common.factory.ResultGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 对于AccessDeniedException的未认证处理，返回json格式的信息
 */
public class AccessDeniedJsonHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public AccessDeniedJsonHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(objectMapper.writeValueAsString(ResultGenerator.getFailResult(new UnauthorizedException())));
    }
}
