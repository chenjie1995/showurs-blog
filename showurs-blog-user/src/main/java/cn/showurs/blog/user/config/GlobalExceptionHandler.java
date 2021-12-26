package cn.showurs.blog.user.config;

import cn.showurs.blog.common.enumz.BusinessCode;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.exception.UnauthorizedException;
import cn.showurs.blog.common.factory.ResultGenerator;
import cn.showurs.blog.common.vo.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 缺省异常处理
     *
     * @param request   请求
     * @param response  响应
     * @param exception 异常
     * @return 异常信息
     */
    @ExceptionHandler
    public Result<Void> defaultExceptionHandler(HttpServletRequest request,
                                             HttpServletResponse response,
                                             Exception exception) {
        log.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        exception.printStackTrace();
        response.setStatus(BusinessCode.ERROR.getCode());
        return ResultGenerator.getFailResult(BusinessCode.ERROR.getCode(), BusinessCode.ERROR.getText());
    }

    /**
     * 方法参数类型不匹配异常
     *
     * @param request   请求
     * @param response  响应
     * @param exception 异常
     * @return 异常信息
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<Void> MethodArgumentTypeMismatchExceptionHandler(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                MethodArgumentTypeMismatchException exception) {
        log.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        response.setStatus(BusinessCode.UNSUPPORTED_MEDIA_TYPE.getCode());
        return ResultGenerator.getFailResult(BusinessCode.UNSUPPORTED_MEDIA_TYPE.getCode(), "方法参数类型不匹配");
    }

    /**
     * 请求体格式错误异常
     *
     * @param request   请求
     * @param response  响应
     * @param exception 异常
     * @return 异常信息
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> httpMessageNotReadableExceptionHandler(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            HttpMessageNotReadableException exception) {
        log.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        response.setStatus(BusinessCode.UNSUPPORTED_MEDIA_TYPE.getCode());
        return ResultGenerator.getFailResult(BusinessCode.UNSUPPORTED_MEDIA_TYPE.getCode(), "请求的JSON格式错误");
    }

    /**
     * 请求参数校验失败异常
     *
     * @param request   请求
     * @param response  响应
     * @param exception 异常
     * @return 异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> MethodArgumentNotValidExceptionHandler(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            MethodArgumentNotValidException exception) {
        log.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        String message = exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        response.setStatus(BusinessCode.UNPROCESSABLE_ENTITY.getCode());
        return ResultGenerator.getFailResult(BusinessCode.UNPROCESSABLE_ENTITY.getCode(), message);
    }

    /**
     * 业务异常处理
     *
     * @param request   请求
     * @param response  响应
     * @param exception 异常
     * @return 异常信息
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> businessExceptionHandler(HttpServletRequest request,
                                              HttpServletResponse response,
                                              BusinessException exception) {
        log.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        response.setStatus(exception.getCode());
        return ResultGenerator.getFailResult(exception.getCode(), exception.getMessage());
    }

    /**
     * 未授权异常处理
     *
     * @param request   请求
     * @param response  响应
     * @param exception 异常
     * @return 异常信息
     */
    @ExceptionHandler(UnauthorizedException.class)
    public Result<Void> unauthorizedExceptionHandler(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  BusinessException exception) {
        log.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        response.setStatus(exception.getCode());
        return ResultGenerator.getFailResult(exception.getCode(), exception.getMessage());
    }
}
