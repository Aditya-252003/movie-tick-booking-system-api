package com.example.mtbs.enitity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TheaterOwner extends UserDetails {

    @OneToMany(mappedBy = "theaterOwner" , cascade = CascadeType.ALL)
    private List<Theater> theaters;

}
