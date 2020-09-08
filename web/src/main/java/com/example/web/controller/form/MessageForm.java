package com.example.web.controller.form;

import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageForm {

    @NotNull @Size(min=5, max=255)
    private String title;
    @Size(max=500)
    private String content;
    @NotNull @Size(min=3, max=255)
    private String sender;
    @NotNull @URL @Size(max=2000)
    private String url;

    public MessageForm() {
    }

    public MessageForm(String title, String content, String sender, String url) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.url = url;
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
}
