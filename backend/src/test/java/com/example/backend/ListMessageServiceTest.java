package com.example.backend;

import com.example.backend.persistence.MessageEntity;
import com.example.backend.persistence.MessageEntityRepository;
import com.example.common.MessageDTO;
import com.example.common.MessageList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ListMessageServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private MessageEntityRepository messageEntityRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;
    @Autowired
    private MappingJackson2XmlHttpMessageConverter jacksonXmlConverter;


    @BeforeAll
    void setUp() {
        //Initialize DB
        messageEntityRepository.save(new MessageEntity("Spring Boot", "Spring Boot Website", "Alireza", "https://spring.io/projects/spring-boot"));
        messageEntityRepository.save(new MessageEntity("JPA JSR", "JSR 338: Java Persistence 2.2", "Alireza", "https://jcp.org/en/jsr/detail?id=338"));
        messageEntityRepository.save(new MessageEntity("Home Page", "Personal Home Page", "Alireza", "https://sites.google.com/view/alireza-haghighatkhah/"));
        messageEntityRepository.save(new MessageEntity("LinkedIn", "LinkedIn Profile", "Alireza", "https://www.linkedin.com/in/haghighatkhah/"));
        //
        jacksonObjectMapper.registerModule(new JaxbAnnotationModule());
        jacksonXmlConverter.getObjectMapper().registerModule(new JaxbAnnotationModule());
    }

    @Test
    public void shouldShowOneMessage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/messages/1").contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        MessageDTO message = jacksonObjectMapper.readValue(mvcResult.getResponse().getContentAsString(), MessageDTO.class);
        assert message.getId() == 1;
        assert "Spring Boot".equals(message.getTitle());
    }

    @Test
    public void shouldListMessagesWithXmlMediaType() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/messages?mediaType="+ MediaType.APPLICATION_XML.getSubtype())
                .contentType("application/xml"))
                .andExpect(status().isOk()).andReturn();
        MessageList messageList = jacksonXmlConverter.getObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), MessageList.class);
        assert messageList.getMessages().size() == 4;
    }

    @Test
    public void shouldListMessagesWithEmptyRequestParameter() throws Exception {
        mockMvc.perform(get("/messages")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("messages", hasSize(4))).andReturn();
    }

    @Test
    public void shouldListMessagesWithJsonMediaType() throws Exception {
        mockMvc.perform(get("/messages?mediaType="+ MediaType.APPLICATION_JSON.getSubtype())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("messages", hasSize(4)));
    }
}
