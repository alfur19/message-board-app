package com.example.backend.config;

import com.example.backend.config.mediatype.SupportedMediaType;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
public class ContentNegotiationConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //set path extension to false
        configurer.favorPathExtension(false).
                //request parameter ("format" by default) should be used to determine the requested media type
                        favorParameter(true).
                //the favour parameter is set to "mediaType" instead of default "format"
                        parameterName("mediaType").
                //ignore the accept headers
                        ignoreAcceptHeader(true).
                //dont use Java Activation Framework since we are manually specifying the mediatypes required below
                        useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON);

        //Dynamically add supported media types to ContentNegotiationConfigurer
        for (SupportedMediaType supportedMediaType: SupportedMediaType.values()) {
            configurer.mediaType(supportedMediaType.getMediaType().getSubtype(), supportedMediaType.getMediaType());
        }
    }
}
