package com.example.backend.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class MessageEntity {

    private @Id @GeneratedValue Long id;
    private String title;
    private @Column(length = 500) String content;
    private String sender;
    private @Column(length = 2000) String url;

    public MessageEntity() {
    }

    public MessageEntity(Long id, String title, String content, String sender, String url) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.url = url;
    }

    public MessageEntity(String title, String content, String sender, String url) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MessageEntity))
            return false;
        MessageEntity messageEntity = (MessageEntity) o;
        return Objects.equals(this.getId(), messageEntity.getId())
                && Objects.equals(this.getTitle(), messageEntity.getTitle())
                && Objects.equals(this.getSender(), messageEntity.getSender())
                && Objects.equals(this.getUrl(), messageEntity.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getTitle(), this.getSender(), this.getUrl());
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
