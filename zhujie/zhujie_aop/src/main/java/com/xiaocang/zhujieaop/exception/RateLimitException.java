package com.xiaocang.zhujieaop.exception;

public class RateLimitException extends RuntimeException {
    private final int code;

    public RateLimitException(String message) {
        this(429, message);  // 429 = Too Many Requests
    }

    public RateLimitException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}