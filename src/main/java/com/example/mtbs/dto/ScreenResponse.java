package com.example.mtbs.dto;

import com.example.mtbs.enums.ScreenType;
import lombok.Builder;

import java.util.List;

@Builder
public record ScreenResponse(
        String screenId,
        ScreenType screenType,
        Integer capacity,
        Integer noOfRows,
        List<SeatsResponse> seatsResponses
){}
