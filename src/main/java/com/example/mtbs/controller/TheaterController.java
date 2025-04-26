package com.example.mtbs.controller;

import com.example.mtbs.dto.TheaterRequest;
import com.example.mtbs.dto.TheaterResponse;
import com.example.mtbs.service.TheaterService;
import com.example.mtbs.util.ResponseStructure;
import com.example.mtbs.util.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/theater")
    public ResponseEntity<ResponseStructure<TheaterResponse>> theaterRegister(@RequestBody @Valid TheaterRequest request , @RequestParam String email){
        TheaterResponse theaterResponse = theaterService.theaterRegister(request , email);
        return restResponseBuilder.success(HttpStatus.CREATED ,"Theater Added successfully",theaterResponse);
    }


    @GetMapping("/theaters/{theaterId}")
    public ResponseEntity<ResponseStructure<TheaterResponse>> findTheater( @PathVariable String theaterId){
        TheaterResponse theaterResponse = theaterService.findTheater(theaterId);
        return restResponseBuilder.success(HttpStatus.OK, "Theater has been sucessfully fetched", theaterResponse);
    }

    @PutMapping("/theaters/{theaterId}")
    public ResponseEntity<ResponseStructure<TheaterResponse>> updateTheater(@PathVariable String theaterId, @Valid @RequestBody TheaterRequest registerationRequest){
        TheaterResponse theaterResponse = theaterService.updateTheater(theaterId, registerationRequest);
        return restResponseBuilder.success(HttpStatus.OK, "Theater has been sucessfully Updated", theaterResponse);
    }

}
