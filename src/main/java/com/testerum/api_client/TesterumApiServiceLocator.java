package com.testerum.api_client;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.testerum.api_client.api.demo.DemoApi;
import com.testerum.api_client.api.features.FeatureApi;
import com.testerum.api_client.api.project.ProjectApi;
import com.testerum.api_client.api.tests.TestsApi;
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
    private String projectPath;

    private ObjectMapper objectMapper = getObjectMapper();

    private TesterumApiServiceLocator(int port) {
        this.port = port;
    }

    public static TesterumApiServiceLocator getInstance(int testerumPort) {
        return new TesterumApiServiceLocator(testerumPort);
    }

    public void setCurrentProject(String projectPath) {
        this.projectPath = projectPath;
    }

    public ProjectApi getProjectApi() {
        return getFeignBuilder().target(ProjectApi.class, getBaseUrl());
    }

    public DemoApi getDemoApi() {
        return getFeignBuilder().target(DemoApi.class, getBaseUrl());
    }

    public FeatureApi getFeatureApi() {
        return getFeignBuilder().target(FeatureApi.class, getBaseUrl());
    }

    public TestsApi getTestApi() {
        return getFeignBuilder().target(TestsApi.class, getBaseUrl());
    }

    //====== Private Methods =========================================
    private Builder getFeignBuilder() {
        return Feign.builder()
                    .requestInterceptor(
                        new RequestInterceptor() {
                            public void apply(RequestTemplate requestTemplate) {
                                requestTemplate.header("Content-Type", "application/json");
                                requestTemplate.header("X-Testerum-Project", projectPath);
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
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

}
