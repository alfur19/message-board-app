package com.example.backend.service.impl.soap;

import com.example.backend.persistence.MessageEntity;
import com.example.backend.persistence.MessageEntityRepository;
import com.example.backend.service.SaveMessageService;
import io.spring.guides.gs_producing_web_service.Message;
import io.spring.guides.gs_producing_web_service.SaveMessageRequest;
import io.spring.guides.gs_producing_web_service.SaveMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SaveMessageServiceEndpoint implements SaveMessageService {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private MessageEntityRepository repository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveMessageRequest")
    @ResponsePayload
    public SaveMessageResponse saveMessage(@RequestPayload SaveMessageRequest request) {
        MessageEntity messageEntity = repository.save(makeMessageEntity(request));
        boolean savedFlag = false;
        if(messageEntity.getId() != null) savedFlag=true;
        SaveMessageResponse response = new SaveMessageResponse();
        response.setSaved(savedFlag);
        return response;
    }

    private MessageEntity makeMessageEntity(SaveMessageRequest saveMessageRequest){
        Message requestMessage = saveMessageRequest.getMessage();
        return new MessageEntity(requestMessage.getTitle(), requestMessage.getContent(), requestMessage.getSender(), requestMessage.getUrl());
    }
}
