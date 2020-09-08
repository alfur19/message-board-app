package com.example.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "message")
public class MessageDTO implements DataTransferObject{
    @XmlElement
    private Long id;
    @XmlElement
    private String title;
    @XmlElement
    private String content;
    @XmlElement
    private String sender;
    @XmlElement @JsonInclude(Include.NON_NULL)
    private String url;

    public MessageDTO() { }

    public MessageDTO(Long id, String title, String content, String sender, String url) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.url = url;
    }

    public MessageDTO(Long id, String title, String content, String sender) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sender = sender;
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
        if (!(o instanceof MessageDTO))
            return false;
        MessageDTO message = (MessageDTO) o;
        return Objects.equals(this.getId(), message.getId())
                && Objects.equals(this.getTitle(), message.getTitle())
                && Objects.equals(this.getSender(), message.getSender())
                && Objects.equals(this.getUrl(), message.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getTitle(), this.getSender(), this.getUrl());
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
