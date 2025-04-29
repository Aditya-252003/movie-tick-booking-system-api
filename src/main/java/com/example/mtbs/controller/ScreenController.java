package com.example.mtbs.controller;

import com.example.mtbs.dto.ScreenRequest;
import com.example.mtbs.dto.ScreenResponse;
import com.example.mtbs.service.ScreenService;
import com.example.mtbs.util.ResponseStructure;
import com.example.mtbs.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ScreenController {

    private ScreenService screenService;
    private RestResponseBuilder responseBuilder;

    @PostMapping("theater/{theaterId}")
    public ResponseEntity<ResponseStructure<ScreenResponse>> registerScreen(@RequestBody ScreenRequest request , @PathVariable String theaterId){
        ScreenResponse screenResponse = screenService.registerScreen(request , theaterId);
        return responseBuilder.success(HttpStatus.CREATED,"Screen Sucessfully created",screenResponse);
    }

    @GetMapping("theaters/{theaterId}/screens/{screenId}")
    public ResponseEntity<ResponseStructure<ScreenResponse>> findScreenById(@PathVariable String theaterId , @PathVariable String screenId){
        ScreenResponse screenResponse = screenService.findScreenById(theaterId,screenId);
        return responseBuilder.success(HttpStatus.CREATED,"Screen data sucessfully fetched",screenResponse);
    }

}
