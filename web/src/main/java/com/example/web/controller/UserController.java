package com.example.web.controller;

import com.example.common.MessageList;
import com.example.web.autogen.consumingwebservice.wsdl.Message;
import com.example.web.autogen.consumingwebservice.wsdl.SaveMessageRequest;
import com.example.web.component.UserSessionBean;
import com.example.web.controller.form.MessageForm;
import com.example.web.soapclient.MessageServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.text.MessageFormat;

@Controller
public class UserController {

    @Autowired
    private UserSessionBean userSessionBean;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MessageServiceClient messageServiceClient;
    @Value("${webservice.address}")
    private String webserviceAddress;


    @GetMapping("/minimal")
    public String minimal(Model model) {
        model.addAttribute("messageList", retrieveMessages());
        model.addAttribute("isMinimalView", true);
        return "list";
    }

    @GetMapping("/listXml")
    public String listXml(Model model) {
        model.addAttribute("messageList", retrieveMessages(MediaType.APPLICATION_XML));
        return "list";
    }

    @GetMapping("/listJson")
    public String listJson(Model model) {
        model.addAttribute("messageList", retrieveMessages(MediaType.APPLICATION_JSON));
        return "list";
    }

    @PostMapping("/save")
    public String save(@Valid MessageForm messageForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "saveForm";
        }
        SaveMessageRequest request = new SaveMessageRequest();
        request.setMessage(makeMessageObject(messageForm));
        messageServiceClient.saveMessage(request);
        return "redirect:/listJson";
    }

    @GetMapping("/saveForm")
    public String saveForm(MessageForm messageForm){
        return "saveForm";
    }

    private MessageList retrieveMessages(){
        String serviceTarget = MessageFormat.format("{0}/messages", webserviceAddress);
        return restTemplate.getForObject(serviceTarget, MessageList.class);
    }

    private MessageList retrieveMessages(MediaType mediaType){
        String serviceTarget = MessageFormat.format("{0}/messages?mediaType={1}", webserviceAddress, mediaType.getSubtype());
        return restTemplate.getForObject(serviceTarget, MessageList.class);
    }

    private Message makeMessageObject(MessageForm messageForm){
        Message message = new Message();
        message.setTitle(messageForm.getTitle());
        message.setContent(messageForm.getContent());
        message.setSender(messageForm.getSender());
        message.setUrl(messageForm.getUrl());
        return message;
    }
}
