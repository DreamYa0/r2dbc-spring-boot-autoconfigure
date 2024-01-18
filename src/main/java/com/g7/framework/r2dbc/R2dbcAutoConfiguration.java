package com.g7.framework.r2dbc;

import com.g7.framework.r2dbc.properties.R2dbcDatabaseProperties;
import com.g7.framework.r2dbc.routing.R2dbcConnectionPoolFactory;
import com.g7.framework.r2dbc.routing.R2dbcRoutingConnectionFactory;
import com.g7.framework.r2dbc.routing.R2dbcRoutingConnectionFactoryAspect;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@AutoConfiguration
@EnableR2dbcAuditing
@EnableTransactionManagement
@EnableConfigurationProperties({R2dbcDatabaseProperties.class})
@EnableR2dbcRepositories(basePackages = "com.**.dao.repository")
public class R2dbcAutoConfiguration extends AbstractR2dbcConfiguration {

    private final R2dbcDatabaseProperties r2dbcDataBaseProperties;

    public R2dbcAutoConfiguration(R2dbcDatabaseProperties r2dbcDataBaseProperties) {
        this.r2dbcDataBaseProperties = r2dbcDataBaseProperties;
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(value = ConnectionFactory.class)
    public ConnectionFactory connectionFactory() {
        return new R2dbcRoutingConnectionFactory(
                new R2dbcConnectionPoolFactory(r2dbcDataBaseProperties));
    }

    @Bean("transactionManager")
    @ConditionalOnMissingBean(value = ReactiveTransactionManager.class)
    public ReactiveTransactionManager reactiveTransactionManager(@Autowired ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    @ConditionalOnMissingBean(value = R2dbcRoutingConnectionFactoryAspect.class)
    public R2dbcRoutingConnectionFactoryAspect dynamicDataSourceAspect() {
        return new R2dbcRoutingConnectionFactoryAspect();
    }
}
