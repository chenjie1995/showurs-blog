package cn.showurs.blog.article.config;

import cn.showurs.blog.common.enumz.ResultCode;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.exception.UnauthorizedException;
import cn.showurs.blog.common.util.ResultGenerator;
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


/**
 * 全局异常处理
 * Created by CJ on 2018/12/8 22:51.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 缺省异常处理
     *
     * @param request   请求
     * @param response  响应
     * @param exception 异常
     * @return 异常信息
     */
    @ExceptionHandler
    public Result defaultExceptionHandler(HttpServletRequest request,
                                          HttpServletResponse response,
                                          Exception exception) {
        logger.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        exception.printStackTrace();
        response.setStatus(ResultCode.ERROR.getCode());
        return ResultGenerator.genFailResult(ResultCode.ERROR.getCode(), ResultCode.ERROR.getDescription());
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
    public Result MethodArgumentTypeMismatchExceptionHandler(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             MethodArgumentTypeMismatchException exception) {
        logger.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        response.setStatus(ResultCode.UNSUPPORTED_MEDIA_TYPE.getCode());
        return ResultGenerator.genFailResult(ResultCode.UNSUPPORTED_MEDIA_TYPE.getCode(), "方法参数类型不匹配");
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
    public Result httpMessageNotReadableExceptionHandler(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         HttpMessageNotReadableException exception) {
        logger.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        response.setStatus(ResultCode.UNSUPPORTED_MEDIA_TYPE.getCode());
        return ResultGenerator.genFailResult(ResultCode.UNSUPPORTED_MEDIA_TYPE.getCode(), "请求的JSON格式错误");
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
    public Result MethodArgumentNotValidExceptionHandler(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         MethodArgumentNotValidException exception) {
        logger.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        String message = exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        response.setStatus(ResultCode.UNPROCESSABLE_ENTITY.getCode());
        return ResultGenerator.genFailResult(ResultCode.UNPROCESSABLE_ENTITY.getCode(), message);
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
    public Result businessExceptionHandler(HttpServletRequest request,
                                           HttpServletResponse response,
                                           BusinessException exception) {
        logger.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        response.setStatus(exception.getCode());
        return ResultGenerator.genFailResult(exception.getCode(), exception.getMessage());
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
    public Result unauthorizedExceptionHandler(HttpServletRequest request,
                                               HttpServletResponse response,
                                               BusinessException exception) {
        logger.warn("Uri:{}, Method:{}, Exception:{}", request.getRequestURI(), request.getMethod(), exception.toString());
        response.setStatus(exception.getCode());
        return ResultGenerator.genFailResult(exception.getCode(), exception.getMessage());
    }

}
