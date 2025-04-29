package com.example.mtbs.enitity;

import com.example.mtbs.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;
    private LocalDate dateOfBirth;
    private boolean isDelete;
    private Instant deletedAt;

    @CreatedDate
    private Instant createdAt;
    @CreatedDate
    private Instant updatedAt;

}
