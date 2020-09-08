package com.example.web.soapclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class MessageServiceClientConfiguration {

    @Value("${webservice.address}")
    private String webserviceAddress;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.web.autogen.consumingwebservice.wsdl");
        return marshaller;
    }

    @Bean
    public MessageServiceClient messageServiceClient(Jaxb2Marshaller marshaller) {
        MessageServiceClient client = new MessageServiceClient();
        client.setDefaultUri(webserviceAddress+"/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}

