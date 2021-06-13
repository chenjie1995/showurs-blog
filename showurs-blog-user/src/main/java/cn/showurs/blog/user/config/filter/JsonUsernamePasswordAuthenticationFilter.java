package cn.showurs.blog.user.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String PATTERN = "/login";
    private static final String HTTP_METHOD = "POST";

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private boolean postOnly = true;

    public JsonUsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher(PATTERN, HTTP_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals(HTTP_METHOD)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        return null;
    }
}
