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
 * @title 表公共基础字段
 * @date 2022/03/04 下午14:43
 * @since 1.0.0
 */
public abstract class AbstractEntity<T extends Serializable> implements Persistable<T> {

    // 主键ID
    @Id @Column(value = "id") private @Nullable T id;
    // 创建时间
    @CreatedDate @Column(value = "gmt_create") private LocalDateTime gmtCreate;
    // 更新时间
    @LastModifiedDate @Column(value = "gmt_modified") private LocalDateTime gmtModified;

    @Nullable
    @Override
    public T getId() {
        return id;
    }

    public void setId(@Nullable T id) {
        this.id = id;
    }

    @Nullable
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(@Nullable LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Nullable
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(@Nullable LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Transient
    @Override
    public boolean isNew() {
        return null == getId();
    }
}
