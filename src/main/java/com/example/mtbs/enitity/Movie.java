package com.example.mtbs.enitity;

import com.example.mtbs.enums.Certificate;
import com.example.mtbs.enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String movieId;
    private String title;
    private String descripition;
    private Duration runtime;

    private List<String> cast;

    @Enumerated(EnumType.STRING)
    private Certificate certificate;

    @Enumerated(EnumType.STRING)
    private Genre genre;



}
