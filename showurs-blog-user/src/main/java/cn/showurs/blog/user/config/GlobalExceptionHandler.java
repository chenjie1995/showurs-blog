package cn.showurs.blog.user.config;

import cn.showurs.blog.user.constant.ResultCode;
import cn.showurs.blog.user.exception.BusinessException;
import cn.showurs.blog.user.exception.UnauthorizedException;
import cn.showurs.blog.user.util.ResultGenerator;
import cn.showurs.blog.user.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
     * @param request 请求
     * @param response 响应
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
     * 业务异常处理
     * @param request 请求
     * @param response 响应
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
     * @param request 请求
     * @param response 响应
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
