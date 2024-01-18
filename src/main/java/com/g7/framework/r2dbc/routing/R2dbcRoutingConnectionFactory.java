package com.g7.framework.r2dbc.routing;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.r2dbc.connection.lookup.AbstractRoutingConnectionFactory;
import reactor.core.publisher.Mono;

import java.util.Map;

public class R2dbcRoutingConnectionFactory extends AbstractRoutingConnectionFactory {

    public static final String DB_KEY = "R2DBC-DB";
    // 默认数据源名称
    private static final String MASTER = "master";

    public R2dbcRoutingConnectionFactory(R2dbcConnectionPoolFactory modeConnectionFactory) {
        Map<String, ConnectionFactory> connectionFactoryMap = modeConnectionFactory.create();
        setTargetConnectionFactories(connectionFactoryMap);
        ConnectionFactory defaultTarget = connectionFactoryMap.get(MASTER);
        setDefaultTargetConnectionFactory(defaultTarget);
    }

    @Override
    protected Mono<Object> determineCurrentLookupKey() {
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey(DB_KEY)) {
                return Mono.create(sink -> sink.success(contextView.get(DB_KEY)));
            } else {
                return Mono.empty();
            }
        });
    }
}
