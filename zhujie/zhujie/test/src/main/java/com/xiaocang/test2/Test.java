package com.xiaocang.test2;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        Class<?> class1 = Class.forName("com.xiaocang.test2.InitDemo");
        Method[]  methods = class1.getMethods();
        if(methods!=null){

            for (Method method : methods) {
                boolean annotationPresent = method.isAnnotationPresent(InitMethod.class);
                if(annotationPresent){
                    method.invoke(class1.getConstructor(null).newInstance(null),null);
                }
            }


        }

    }
}
