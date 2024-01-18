package com.g7.framework.r2dbc.transaction;

import com.g7.framework.framwork.exception.BusinessException;
import com.g7.framework.framwork.exception.meta.CommonErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author dreamyao
 * @title 编程式事物基础类
 * @date 2021/11/14 下午20:43
 * @since 1.1.0
 */
public abstract class TransactionWrapper {

    private static final Logger logger = LoggerFactory.getLogger(TransactionWrapper.class);
    private static final String TX_ID = "TxId";

    @Autowired
    private ReactiveTransactionManager reactiveTransactionManager;

    /**
     * 编程事务
     * <p>
     * 有异常则外层捕获 BusinessException
     * @return 方法执行自定义返回值
     * @author dreamyao
     */
    public <T> Flux<T> transaction(final FluxExecutor<T> executor) {

        TransactionalOperator operator = TransactionalOperator.create(reactiveTransactionManager);
        return operator.execute(status -> {
            try {

                return executor.execute();

            } catch (BusinessException e) {
                logger.info("business exception caught in transaction : {}", e.getMessage());
                status.setRollbackOnly();
                throw e;
            } catch (Throwable e) {

                logger.error("TransactionWrapper transaction error : {}", e.getMessage());
                status.setRollbackOnly();

                BusinessException businessException = new BusinessException(CommonErrorCode.TRANSACTION_EXCEPTION);
                businessException.initCause(e);
                throw businessException;
            }
        });
    }

    /**
     * 编程事务
     * <p>
     * 有异常则外层捕获 BusinessException
     * @return 方法执行自定义返回值
     * @author dreamyao
     */
    public <T> Flux<T> transaction(final MonoExecutor<T> executor) {

        TransactionalOperator operator = TransactionalOperator.create(reactiveTransactionManager);
        return operator.execute(status -> {
            try {

                return executor.execute();

            } catch (BusinessException e) {
                logger.info("business exception caught in transaction : {}", e.getMessage());
                status.setRollbackOnly();
                throw e;
            } catch (Throwable e) {

                logger.error("TransactionWrapper transaction error : {}", e.getMessage());
                status.setRollbackOnly();

                BusinessException businessException = new BusinessException(CommonErrorCode.TRANSACTION_EXCEPTION);
                businessException.initCause(e);
                throw businessException;
            }
        });
    }

    @FunctionalInterface
    public interface MonoExecutor<T> {
        Mono<T> execute() throws Exception;
    }

    @FunctionalInterface
    public interface FluxExecutor<T> {
        Flux<T> execute() throws Exception;
    }
}



