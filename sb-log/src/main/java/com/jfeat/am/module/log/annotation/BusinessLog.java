package com.jfeat.am.module.log.annotation;

import java.lang.annotation.*;

/**
 * Created by jackyhuang on 2017/11/16.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BusinessLog {

    /**
     * 业务的名称, 如 用户管理
     */
    String name() default "";

    /**
     * 业务的操作， 如 添加用户
     */
    String value() default "";

}
