package com.example.mtbs.service.impl;

import com.example.mtbs.dto.ScreenRequest;
import com.example.mtbs.dto.ScreenResponse;
import com.example.mtbs.enitity.Screen;
import com.example.mtbs.enitity.Seat;
import com.example.mtbs.enitity.Theater;
import com.example.mtbs.mapper.ScreenMapper;
import com.example.mtbs.repository.ScreenRepository;
import com.example.mtbs.repository.SeatRepository;
import com.example.mtbs.repository.TheaterRepository;
import com.example.mtbs.service.ScreenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScreenServiceimpl implements ScreenService {

    private TheaterRepository theaterRepository;
    private ScreenRepository screenRepository;
    private ScreenMapper screenMapper;
    private SeatRepository seatRepository;


    @Override
    public ScreenResponse registerScreen(ScreenRequest request, String theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new IllegalArgumentException("Theater Id is not found"));

        Screen screen = screenMapper.toEntity(request);

        Screen savedScreen = screenRepository.save(screen);

        return screenMapper.toScreenResponse(savedScreen);
    }

    @Override
    public ScreenResponse findScreenById(String theaterId, String screenId) {
        if(theaterRepository.existsById(theaterId)){
            if(screenRepository.existsById(screenId)){
                return screenMapper.toScreenResponse(screenRepository.findById(screenId).get());
            }
            throw new IllegalArgumentException("Screen not found");
        }
        throw new IllegalArgumentException("Theater not found");
    }

    public List<Seat> registerSeat(Screen screen, Integer capacity) {
        List<Seat> seats = new ArrayList<>();
        int rows = screen.getNoOfRows();
        int seatsPerRow =  screen.getCapacity() / screen.getNoOfRows();

        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= seatsPerRow; col++) {
                Seat seat = new Seat();
                seat.setScreen(screen);
                seat.setSeatname(String.format("%c%d", 'A' + row - 1, col));
                seats.add(seat);
            }
        }
        return seats;
    }
}
