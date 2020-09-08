package com.example.backend.config.mediatype;

import org.springframework.http.MediaType;

public enum SupportedMediaType {
    XML (MediaType.APPLICATION_XML), JSON (MediaType.APPLICATION_JSON);

    private MediaType mediaType;

    SupportedMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public MediaType getMediaType() {
        return mediaType;
    }
}
