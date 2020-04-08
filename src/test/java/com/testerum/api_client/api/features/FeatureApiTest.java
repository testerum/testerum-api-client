package com.testerum.api_client.api.features;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.testerum.api_client.TesterumApiServiceLocator;
import com.testerum.api_client.infrastructure.TesterumApiInfrastructure;
import com.testerum.model.feature.Feature;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeatureApiTest {

    private TesterumApiServiceLocator serviceLocator = TesterumApiInfrastructure.getTesterumApiServiceLocator();

    @BeforeEach
    void beforeAll() {
        serviceLocator.setCurrentProject("tax");
    }

    @Test
    void getAllFeatures() {
        Collection<Feature> allFeatures = serviceLocator.getFeatureApi().getAllFeatures();
        assertNotNull(allFeatures);
    }

    @Test
    void getFeaturesTree() {
    }

    @Test
    void getFeatureAtPath() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void copy() {
    }

    @Test
    void move() {
    }
}
