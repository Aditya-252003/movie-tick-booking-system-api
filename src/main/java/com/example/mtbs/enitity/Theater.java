package com.example.mtbs.enitity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String theaterId;
    private String name;
    private String address;
    private String city;
    private String landmark;

    @CreatedDate
    private Long createdAt;
    @LastModifiedDate
    private Long updatedAt;

    private String createdBy;

    @ManyToOne
    @JoinColumn
    private TheaterOwner theaterOwner;

    @OneToMany
    private List<Screen> screens;

    public void setOwner(TheaterOwner theaterOwner){
    this.setTheaterOwner(theaterOwner);
    }


}
