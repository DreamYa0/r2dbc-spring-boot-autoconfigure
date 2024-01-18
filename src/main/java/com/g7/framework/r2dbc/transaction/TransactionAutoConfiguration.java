package com.g7.framework.r2dbc.transaction;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author dreamyao
 * @title
 * @date 2021/11/14 下午20:43
 * @since 1.0.0
 */
@AutoConfiguration
public class TransactionAutoConfiguration {

    @Bean
    public TransactionProxy transactionProxy() {
        return new TransactionProxy();
    }
}
