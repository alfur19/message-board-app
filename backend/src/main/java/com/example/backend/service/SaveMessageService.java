package com.example.backend.service;

import com.example.backend.service.impl.soap.autogen.SaveMessageRequest;
import com.example.backend.service.impl.soap.autogen.SaveMessageResponse;

public interface SaveMessageService {
    SaveMessageResponse saveMessage(SaveMessageRequest request);
}
