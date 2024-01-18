package com.g7.framework.r2dbc.routing;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SpecifyDataSource {

    /**
     * 指定数据源的名称，对应在apollo中配置 默认主库数据源是master，从库数据源是 slave
     */
    String value() default "master";
}
