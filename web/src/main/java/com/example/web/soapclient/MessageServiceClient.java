package com.example.web.soapclient;

import com.example.web.autogen.wsdl.messageservice.SaveMessageRequest;
import com.example.web.autogen.wsdl.messageservice.SaveMessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class MessageServiceClient extends WebServiceGatewaySupport {

    @Value("${message.endpoint}")
    private String messageServiceEndpoint;

    public SaveMessageResponse saveMessage(SaveMessageRequest request) {
        return (SaveMessageResponse) getWebServiceTemplate().marshalSendAndReceive(messageServiceEndpoint, request);
    }
}
