package com.example.SharmiSpringBoot.UserProfile.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Data
public class BaseEntity {

    public BaseEntity(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String upadtedBy){

        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = upadtedBy;
    }

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    @Column
    private String createdBy;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public BaseEntity() {

    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    @Column
    @LastModifiedBy
    private String updatedBy;
}
