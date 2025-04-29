package com.example.mtbs.service;

import com.example.mtbs.dto.ScreenRequest;
import com.example.mtbs.dto.ScreenResponse;

public interface ScreenService {

    ScreenResponse registerScreen(ScreenRequest request, String theaterId);

    ScreenResponse findScreenById(String theaterId, String screenId);
}
