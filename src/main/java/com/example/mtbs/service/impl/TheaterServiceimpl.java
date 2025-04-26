package com.example.mtbs.service.impl;

import com.example.mtbs.dto.TheaterRequest;
import com.example.mtbs.dto.TheaterResponse;
import com.example.mtbs.enitity.Theater;
import com.example.mtbs.enitity.TheaterOwner;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.mapper.TheaterMapper;
import com.example.mtbs.repository.TheaterRepository;
import com.example.mtbs.repository.UserRepository;
import com.example.mtbs.service.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class TheaterServiceimpl implements TheaterService {

    private final TheaterMapper theaterMapper;
    private final UserRepository userRepository;
    private final TheaterRepository theaterRepository;

    @Override
    public TheaterResponse theaterRegister(TheaterRequest request, String email) {

        UserDetails userDetails = userRepository.findByEmail(email);

        if (!(userDetails instanceof TheaterOwner owner)) {
                     throw new IllegalArgumentException("Failed to find Theater Owner.");
        }

        Theater theater = theaterMapper.toEntity(request);

        theater.setTheaterOwner(theater.getTheaterOwner());

        theaterRepository.save(theater);

        return theaterMapper.toTheaterResponse(theater);
    }

    @Override
    public TheaterResponse findTheater(String theaterId) {
            Theater theater = theaterRepository.findById(theaterId)
                    .orElseThrow(() -> new IllegalArgumentException("Theater Not found by Id"));
            return theaterMapper.toTheaterResponse(theater);
    }

    @Override
    public TheaterResponse updateTheater(String theaterId, TheaterRequest registerationRequest) {
            Theater theater = theaterRepository.findById(theaterId)
                    .orElseThrow(() -> new IllegalArgumentException("Theater Not found by Id"));

        theaterMapper.toEntity(registerationRequest, theater);
            theaterRepository.save(theater);
            return theaterMapper.toTheaterResponse(theater);
    }
}

