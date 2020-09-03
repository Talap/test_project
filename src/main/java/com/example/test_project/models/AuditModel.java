package com.example.test_project.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditModel implements Serializable {

    @Id
    @GeneratedValue(generator = "seq")
    @Column(updatable = false, nullable = false)
    @ApiModelProperty(notes = "Уникальный идентификатор", readOnly = true)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @ApiModelProperty(notes = "Момент создания", readOnly = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    @ApiModelProperty(notes = "Момент последнего изменения", readOnly = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    @ApiModelProperty(notes = "Момент удаления элемента", readOnly = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date deletedAt;


    @PrePersist
    private void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
        this.updatedAt = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = new Date();
    }

}

