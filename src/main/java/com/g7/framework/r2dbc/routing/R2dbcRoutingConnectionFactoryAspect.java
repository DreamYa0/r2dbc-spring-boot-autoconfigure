package com.g7.framework.r2dbc.routing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;

import static com.g7.framework.r2dbc.routing.R2dbcRoutingConnectionFactory.DB_KEY;

@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class R2dbcRoutingConnectionFactoryAspect {

    @Around(value = "@annotation(com.g7.framework.r2dbc.routing.SpecifyDataSource)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        SpecifyDataSource dataSource = method.getAnnotation(SpecifyDataSource.class);
        if (method.getReturnType() == Mono.class) {
            return ((Mono<?>) pjp.proceed()).contextWrite(
                    context -> context.put(DB_KEY, dataSource.value()));

        } else {
            return ((Flux<?>) pjp.proceed()).contextWrite(
                    context -> context.put(DB_KEY, dataSource.value()));
        }
    }
}
