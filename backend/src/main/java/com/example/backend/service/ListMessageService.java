package com.example.backend.service;

import com.example.common.MessageDTO;
import com.example.common.MessageList;
import org.springframework.http.MediaType;

import java.util.List;

public interface ListMessageService {
    MessageList list(String mediaType);
    MessageDTO one(Long id);
    List<MediaType> getSupportedMediaTypes();
}
