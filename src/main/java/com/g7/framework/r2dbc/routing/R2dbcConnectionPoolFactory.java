package com.g7.framework.r2dbc.routing;

import com.g7.framework.r2dbc.properties.ConnectionProperties;
import com.g7.framework.r2dbc.properties.R2dbcDatabaseProperties;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

public class R2dbcConnectionPoolFactory {

    private final Map<String, ConnectionProperties> config;
    private final R2dbcDatabaseProperties dataBaseProperties;

    public R2dbcConnectionPoolFactory(R2dbcDatabaseProperties dataBaseProperties) {
        this.config = dataBaseProperties.getR2dbc();
        this.dataBaseProperties = dataBaseProperties;
    }

    private ConnectionFactory createConnectionFactory(ConnectionProperties properties) {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.parse(properties.getUrl());

        String username = properties.getUsername();
        if (!StringUtils.hasText(username)) {
            username = dataBaseProperties.getUsername();
        }

        String password = properties.getPassword();
        if (!StringUtils.hasText(password)) {
            password = dataBaseProperties.getPassword();
        }

        options = options.mutate()
                .option(USER, username)
                .option(PASSWORD, password)
                .option(CONNECT_TIMEOUT, Duration.ofSeconds(60))
                .option(Option.valueOf("socketTimeout"), Duration.ofSeconds(60)) // optional, default null, null means no timeout
                .option(Option.valueOf("zeroDate"), "use_null") // optional, default "use_null"
                .option(Option.valueOf("useServerPrepareStatement"), true) // optional, default false
                .option(Option.valueOf("tcpKeepAlive"), true) // optional, default false
                .option(Option.valueOf("tcpNoDelay"), true) // optional, default false
                .option(Option.valueOf("autodetectExtensions"), false) // optional, default false
                .build();
        ConnectionFactory connectionFactory = ConnectionFactories.get(options);
        ConnectionPoolConfiguration configuration = ConnectionPoolConfiguration
                .builder(connectionFactory)
                .maxIdleTime(Duration.ofMillis(600000))
                .maxLifeTime(Duration.ofMillis(1800000))
                .maxSize(10)
                .initialSize(10)
                .validationQuery("select 1")
                .maxCreateConnectionTime(Duration.ofMillis(30000))
                .maxAcquireTime(Duration.ofMillis(60000))
                .build();
        return new ConnectionPool(configuration);
    }

    public Map<String, ConnectionFactory> create() {
        Map<String, ConnectionFactory> connectionFactoryMap = new HashMap<>();
        for (Map.Entry<String, ConnectionProperties> entry : config.entrySet()) {
            connectionFactoryMap.put(entry.getKey(), createConnectionFactory(entry.getValue()));
        }
        return connectionFactoryMap;
    }
}
