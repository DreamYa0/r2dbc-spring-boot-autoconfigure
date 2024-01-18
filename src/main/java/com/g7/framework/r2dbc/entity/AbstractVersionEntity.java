package com.g7.framework.r2dbc.entity;

import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

/**
 * @author dreamyao
 * @title
 * @date 2022/03/04 下午14:43
 * @since 1.0.0
 */
public abstract class AbstractVersionEntity<T extends Serializable> extends AbstractEntity<T> {

    // 版本，使用做乐观锁用
    @Version @Column(value = "version") private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
