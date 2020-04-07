package com.testerum.api_client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.testerum.api_client.api.project.ProjectApi;
import com.testerum.model.home.CreateProjectRequest;
import com.testerum.model.home.Project;
import feign.Feign;
import feign.Feign.Builder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.jetbrains.annotations.NotNull;

public class TesterumApiServiceLocator {

    private String host = "http://localhost";
    private int port;

    private ObjectMapper objectMapper = getObjectMapper();

    private TesterumApiServiceLocator(int port){
        this.port = port;
    }

    public static TesterumApiServiceLocator getInstance(int testerumPort) {
        return new TesterumApiServiceLocator(testerumPort);
    }

    public ProjectApi getProjectApi() {
        return getFeignBuilder().target(ProjectApi.class, getBaseUrl() + "/projects");
    }

//====== Private Methods =========================================
    private Builder getFeignBuilder() {
        return Feign.builder()
                    .requestInterceptor(
                        new RequestInterceptor() {
                            public void apply(RequestTemplate requestTemplate) {
                                requestTemplate.header("Content-Type", "application/json");
                            }
                        }
                    )
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper));
    }

    @NotNull
    private String getBaseUrl() {
        return host + ":" + port + "/rest";
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AfterburnerModule());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new GuavaModule());

        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return objectMapper;
    }

}
