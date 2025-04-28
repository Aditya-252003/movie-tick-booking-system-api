package com.example.mtbs.enitity;

import com.example.mtbs.enums.ScreenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Screen {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;

    @Enumerated(EnumType.STRING)
    private ScreenType screenType;

    private Integer capacity;
    private Integer noOfRows;


    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "screen" ,cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
     private List<Seat> seats;

    @CreatedDate
    private Long createdAt;
    @LastModifiedDate
    private Long updatedAt;

    private String createdBy;
}
