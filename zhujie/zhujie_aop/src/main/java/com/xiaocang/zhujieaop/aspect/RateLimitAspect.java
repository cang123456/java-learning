package com.xiaocang.zhujieaop.aspect;//package com.xiaocang.zhujieaop.aspect;
//
//
//import com.xiaocang.zhujieaop.annotation.RateLimit;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//@Aspect
//public class RateLimitAspect {
//
//
//    private final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();
//
//    @Around("@annotation(rateLimit)")
//    public Object around(ProceedingJoinPoint pjp, RateLimit rateLimit) throws Throwable {
//        String key = pjp.getSignature().toLongString();
//        AtomicInteger count = counters.computeIfAbsent(key, k -> new AtomicInteger());
//
//        if(count.incrementAndGet() > rateLimit.limit()) {
//            throw new RuntimeException("请求超过限制");
//        }
//
//        return pjp.proceed();
//
//    }
//
//}


import com.xiaocang.zhujieaop.annotation.RateLimit;
import com.xiaocang.zhujieaop.exception.RateLimitException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;



// 002 抛出runtimeerro
//@Aspect
//@Component
//public class RateLimitAspect {
//
//    private static class Window {//滑动窗口
//        volatile long startTime = System.currentTimeMillis();
//        final AtomicInteger count = new AtomicInteger(0);
//    }
//
//    private final Map<String, Window> windows = new ConcurrentHashMap<>();
//
//    @Around("@annotation(rateLimit)")
//    public Object around(ProceedingJoinPoint pjp, RateLimit rateLimit) throws Throwable {
//        String key = pjp.getSignature().toLongString();
//        long now = System.currentTimeMillis();
//        Window window = windows.computeIfAbsent(key, k -> new Window());
//
//        synchronized (window) {
//            if (now - window.startTime >= rateLimit.window()) {
//                window.startTime = now;
//                window.count.set(0);
//            }
//            if (window.count.incrementAndGet() > rateLimit.limit()) {
//                throw new RuntimeException("请求超过限制");
//            }
//        }
//
//        return pjp.proceed();
//    }
//}


// 003 抛出自定义异常
@Aspect
@Component
public class RateLimitAspect {

    private static class Window {
        volatile long startTime = System.currentTimeMillis();
        final AtomicInteger count = new AtomicInteger(0);
    }

    private final Map<String, Window> windows = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint pjp, RateLimit rateLimit) throws Throwable {
        String key = pjp.getSignature().toLongString();
        long now = System.currentTimeMillis();
        Window window = windows.computeIfAbsent(key, k -> new Window());

        synchronized (window) {
            if (now - window.startTime >= rateLimit.window()) {
                window.startTime = now;
                window.count.set(0);
            }
            if (window.count.incrementAndGet() > rateLimit.limit()) {
                // 抛自定义异常，而不是 RuntimeException
                throw new RateLimitException("请求太频繁，请稍后再试");
            }
        }

        return pjp.proceed();
    }
}
