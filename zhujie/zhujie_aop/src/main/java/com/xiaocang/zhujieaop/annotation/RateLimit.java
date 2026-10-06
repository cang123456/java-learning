package com.xiaocang.zhujieaop.annotation;



import java.lang.annotation.*;

@Target(ElementType.METHOD)          // 只能用在方法上
@Retention(RetentionPolicy.RUNTIME)  // 运行时可反射读取（关键！）
@Documented
public @interface RateLimit {

    int limit() default 10;
    long window() default 1000;  // 毫秒
    String message() default "请求太频繁，请稍后再试！";

}
