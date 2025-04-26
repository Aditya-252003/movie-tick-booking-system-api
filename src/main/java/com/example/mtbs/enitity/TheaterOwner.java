package com.example.mtbs.enitity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TheaterOwner extends UserDetails {

    private String email;

    @OneToMany(mappedBy = "theaterOwner" , cascade = CascadeType.ALL)
    private List<Theater> theaters = new ArrayList<>();


    public void addTheater (Theater theater){
        theater.setTheaterOwner(this);
    }
}
