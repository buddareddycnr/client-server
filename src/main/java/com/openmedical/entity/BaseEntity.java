package com.openmedical.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Column(name = "CREATED_DATE",nullable = false)
    @CreationTimestamp
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_DATE")
    @UpdateTimestamp
    private Timestamp lastUpdatedDate;
    @Column(name = "CREATED_BY",length = 60,nullable = false)
    @CreatedBy
    private String createdBy;
    @Column(name = "LAST_UPDATED_BY",length = 60)
    @LastModifiedBy
    private String lastUpdatedBy;
}
