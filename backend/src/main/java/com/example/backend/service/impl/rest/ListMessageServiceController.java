package com.example.backend.service.impl.rest;

import com.example.backend.persistence.MessageEntity;
import com.example.backend.persistence.MessageEntityRepository;
import com.example.backend.service.ListMessageService;
import com.example.backend.service.exceptions.MessageNotFoundException;
import com.example.backend.config.mediatype.SupportedMediaType;
import com.example.common.MessageDTO;
import com.example.common.MessageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ListMessageServiceController implements ListMessageService {

    @Autowired
    private MessageEntityRepository repository;

    @GetMapping(path = "/messages", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public MessageList list(@RequestParam(required = false) String mediaType) {
        List<MessageEntity> messageEntityList = repository.findAll();
        List<MessageDTO> messageDTOList = new ArrayList<>();
        MessageDTO messageDTO = null;
        for (MessageEntity entity: messageEntityList) {
            messageDTO = new MessageDTO(entity.getId(), entity.getTitle(), entity.getContent(), entity.getSender());
            if(mediaType != null){
                messageDTO.setUrl(entity.getUrl());
            }
            messageDTOList.add(messageDTO);
        }
        //Sort messages by title
        messageDTOList.sort(Comparator.comparing(MessageDTO::getTitle));
        return new MessageList(messageDTOList);
    }

    @GetMapping(path = "/messages/{id}")
    public MessageDTO one(@PathVariable Long id) {
        MessageEntity messageEntity = repository.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
        return new MessageDTO(messageEntity.getId(), messageEntity.getTitle(), messageEntity.getContent(), messageEntity.getSender(), messageEntity.getUrl());
    }

    @GetMapping(path = "/supportedMediaTypes")
    public List<MediaType> getSupportedMediaTypes(){
        return Arrays.stream(SupportedMediaType.values())
                .map(SupportedMediaType::getMediaType)
                .collect(Collectors.toList());
    }

}
