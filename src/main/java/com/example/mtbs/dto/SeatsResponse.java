package com.example.mtbs.dto;

import lombok.Builder;

@Builder
public record SeatsResponse(

        String seatId,
        String seatname
) {

}
