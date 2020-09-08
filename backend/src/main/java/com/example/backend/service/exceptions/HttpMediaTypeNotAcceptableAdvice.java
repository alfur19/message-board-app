package com.example.backend.service.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HttpMediaTypeNotAcceptableAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Supported media types are [ ");
        ex.getSupportedMediaTypes().forEach(mediaType -> builder.append(mediaType + ";"));
        builder.append(" ]");
        return new ResponseEntity<Object>(builder.toString(), HttpStatus.NOT_ACCEPTABLE);
    }

}
