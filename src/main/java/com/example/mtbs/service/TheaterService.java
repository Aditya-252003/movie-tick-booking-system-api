package com.example.mtbs.service;

import com.example.mtbs.dto.TheaterRequest;
import com.example.mtbs.dto.TheaterResponse;
import jakarta.validation.Valid;

public interface TheaterService {
    TheaterResponse theaterRegister(@Valid TheaterRequest request, String email);

    TheaterResponse findTheater(String theaterId);

    TheaterResponse updateTheater(String theaterId, @Valid TheaterRequest registerationRequest);
}
