package com.xiaocang.zhujieaop.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)          // 只能用在方法上
@Retention(RetentionPolicy.RUNTIME)  // 运行时可反射读取（关键！）
@Documented
public @interface Log {


}
