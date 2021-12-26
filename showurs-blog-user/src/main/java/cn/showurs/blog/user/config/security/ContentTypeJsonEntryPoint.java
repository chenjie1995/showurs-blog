package cn.showurs.blog.user.config.security;

import cn.showurs.blog.common.exception.ForbiddenException;
import cn.showurs.blog.common.factory.ResultGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 身份验证方案，返回ContentType json方式的403 FORBIDDEN信息
 */
public class ContentTypeJsonEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public ContentTypeJsonEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(objectMapper.writeValueAsString(ResultGenerator.getFailResult(new ForbiddenException())));
        printWriter.flush();
        printWriter.close();
    }
}
