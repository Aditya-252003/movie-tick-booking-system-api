package com.example.mtbs.mapper;

import com.example.mtbs.dto.TheaterRequest;
import com.example.mtbs.dto.TheaterResponse;
import com.example.mtbs.enitity.Theater;
import org.springframework.stereotype.Component;

@Component
public class TheaterMapper {

    public TheaterResponse toTheaterResponse(Theater theater){
        return new TheaterResponse(
                theater.getTheaterId(),
                theater.getName(),
                theater.getAddress(),
                theater.getCity(),
                theater.getLandmark(),
                theater.getCreatedAt());
    }

    public Theater toEntity(TheaterRequest request) {
        Theater theater = new Theater();
        theater.setName(request.name());
        theater.setAddress(request.address());
        theater.setCity(request.city());
        theater.setLandmark(request.landmark());

        return theater;
    }

    public void toEntityUpdate(TheaterRequest source, Theater target) {
        target.setName(source.name());
        target.setAddress(source.address());
        target.setCity(source.city());
        target.setLandmark(source.landmark());
    }
}
