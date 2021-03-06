package com.taobao.tcif.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER }) // 用于字段，方法，参数
@Retention(RetentionPolicy.RUNTIME) // 在运行时加载到Annotation到JVM中
public @interface Param {
	String name() default "";
	String type() default "";
}
