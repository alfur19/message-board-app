package com.example.backend.service;

import io.spring.guides.gs_producing_web_service.SaveMessageRequest;
import io.spring.guides.gs_producing_web_service.SaveMessageResponse;

public interface SaveMessageService {
    SaveMessageResponse saveMessage(SaveMessageRequest request);
}
