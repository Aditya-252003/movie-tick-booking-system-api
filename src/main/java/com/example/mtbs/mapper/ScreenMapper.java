package com.example.mtbs.mapper;

import com.example.mtbs.dto.ScreenRequest;
import com.example.mtbs.dto.ScreenResponse;
import com.example.mtbs.dto.SeatsResponse;
import com.example.mtbs.enitity.Screen;
import com.example.mtbs.enitity.Seat;
import com.example.mtbs.enitity.Theater;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class ScreenMapper {

    public ScreenResponse toScreenResponse(Screen screen) {
        return new ScreenResponse(
                screen.getScreenId(),
                screen.getScreenType(),
                screen.getCapacity(),
                screen.getNoOfRows(),
                seatResponseMapper(screen.getSeats())
        );
    }

    public Screen toEntity(ScreenRequest screenRequest) {
        Screen screen = new Screen();
        screen.setScreenType(screenRequest.screenType());
        screen.setCapacity(screenRequest.capacity());
        screen.setNoOfRows(screenRequest.noOfRows());
        return screen;
    }

    public List<SeatsResponse> seatResponseMapper(List<Seat> seats) {
        System.out.println(seats);
        List<SeatsResponse> seatsResponses = new LinkedList<>();
        for (Seat seat : seats) {
            seatsResponses.add(
                    SeatsResponse.builder()
                            .seatId((seat.getSeatId()))
                            .seatname((seat.getSeatname()))
                            .build()
            );
        }
        return seatsResponses;
    }
}
