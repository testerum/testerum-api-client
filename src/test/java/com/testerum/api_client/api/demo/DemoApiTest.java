package com.testerum.api_client.api.demo;

import static org.junit.jupiter.api.Assertions.*;

import com.testerum.api_client.TesterumApiServiceLocator;
import com.testerum.api_client.infrastructure.TesterumApiInfrastructure;
import org.junit.jupiter.api.Test;

class DemoApiTest {

    private TesterumApiServiceLocator serviceLocator = TesterumApiInfrastructure.getTesterumApiServiceLocator();

    @Test
    void startDemoApp() {
        serviceLocator.getDemoApi().startDemoApp();
    }
}
