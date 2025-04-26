package com.example.mtbs.enitity;

import com.example.mtbs.enums.ScreenType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Screen {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;
    private ScreenType screenType;
    private String capacity;
}
