package com.example.backend;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.backend.persistence.MessageEntity;
import com.example.backend.persistence.MessageEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageEntityRepositoryTest {

    @Autowired
    MessageEntityRepository repository;

    @Test
    public void checkEmptyRepository() {
        Iterable<MessageEntity> allMessages = repository.findAll();
        assertThat(allMessages).isEmpty();
    }

    @Test
    public void shouldSaveAndListMessageEntity(){
        MessageEntity messageEntity1 = new MessageEntity("Test Title 1", "Content 1", "Alireza", "http://www.google.com");
        MessageEntity messageEntity2 = new MessageEntity("Test Title 2", "Content 2", "Alireza", "http://www.google.com");
        MessageEntity messageEntity3 = new MessageEntity("Test Title 3", "Content 3", "Alireza", "http://www.google.com");
        repository.save(messageEntity1);
        repository.save(messageEntity2);
        repository.save(messageEntity3);
        //
        List<MessageEntity> allMessages = repository.findAll();
        assertThat(allMessages).hasSize(3).extracting(MessageEntity::getId).doesNotContainNull();
        assertThat(allMessages).hasSize(3).extracting(MessageEntity::getTitle).containsOnly(messageEntity1.getTitle(), messageEntity2.getTitle(), messageEntity3.getTitle());
        //
        Optional<MessageEntity> foundMessageEntity1 = repository.findById(1L);
        assertThat(foundMessageEntity1.isPresent());
        assertThat(foundMessageEntity1.get()).isEqualTo(messageEntity1);
    }

    @Test
    public void shouldRemoveAllMessageEntity(){
        repository.deleteAll();
        List<MessageEntity> allMessages = repository.findAll();
        assertThat(allMessages).hasSize(0);
    }

}
