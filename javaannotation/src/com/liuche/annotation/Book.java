package com.liuche.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.METHOD})
public @interface Book {
    String name() default "情深深雨蒙蒙";
    String[] authors() default  {"狗蛋","翠花"};
    double price() default 100.0;
}
