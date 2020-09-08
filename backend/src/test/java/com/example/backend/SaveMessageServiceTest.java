package com.example.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.junit.Before;
import java.io.IOException;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = WebServiceConfig.class)
@SpringBootTest
public class SaveMessageServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockWebServiceClient;
    private final Resource xsdSchema = new ClassPathResource("messages.xsd");
    private final Resource requestPayload = new ClassPathResource("requestPayload.xml");
    private final Resource responsePayload = new ClassPathResource("responsePayload.xml");

    @Before
    public void init(){
        mockWebServiceClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void shouldSaveMessage() throws IOException {
        mockWebServiceClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(validPayload(xsdSchema))
                .andExpect(payload(responsePayload))
                ;
    }

}


