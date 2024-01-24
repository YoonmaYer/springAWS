package com.yoonmaYer.book.springboot.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
    @MappedSuperclass   // JPA 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식되게끔
    @EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity 클래스에 Auditing 기능 포함
    public abstract class BaseTimeEntity {

        @CreatedDate    // Entity가 생성되어 저장될 때 시간 자동 저장
        private LocalDateTime createDate;

        @LastModifiedDate   // 조회한 Entity의 값을 변경할 때 시간 자동 저장
        private LocalDateTime modifiedDate;
    }
