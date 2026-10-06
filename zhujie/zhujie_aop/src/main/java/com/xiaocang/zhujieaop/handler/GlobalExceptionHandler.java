package com.xiaocang.zhujieaop.handler;

import com.xiaocang.zhujieaop.pojo.Result;
import com.xiaocang.zhujieaop.exception.RateLimitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理限流异常
     * 注意：这里用 warn 而不是 error，因为限流是预期内的保护机制，不是系统故障
     */
    @ExceptionHandler(RateLimitException.class)
    public Result<Void> handleRateLimit(RateLimitException e) {
        log.warn("限流触发: {}", e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 兜底：处理其他未捕获异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常", e);  // 这里才是真正的 error，需要打印堆栈
        return Result.fail(500, "系统繁忙，请稍后重试");
    }
}