package com.example.mtbs.enitity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String showId;
    private Long startsAt;
    private Long endsAt;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
    @CreatedBy
    private String createdBy;


    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Theater theater;


}
