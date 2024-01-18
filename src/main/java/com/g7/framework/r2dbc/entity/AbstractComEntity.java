package com.g7.framework.r2dbc.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author dreamyao
 * @title 表公共基础字段（通用）
 * @date 2022/03/04 下午14:43
 * @since 1.0.0
 */
public abstract class AbstractComEntity<T extends Serializable> implements Persistable<T> {

    // 主键ID
    @Id @Column(value = "id") private @Nullable T id;
    // 创建时间
    @CreatedDate @Column(value = "create_time") private LocalDateTime createTime;
    // 更新时间
    @LastModifiedDate @Column(value = "update_time") private LocalDateTime updateTime;

    @Nullable
    @Override
    public T getId() {
        return id;
    }

    public void setId(@Nullable T id) {
        this.id = id;
    }

    @Nullable
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(@Nullable LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Nullable
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(@Nullable LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Transient
    @Override
    public boolean isNew() {
        return null == getId();
    }
}
