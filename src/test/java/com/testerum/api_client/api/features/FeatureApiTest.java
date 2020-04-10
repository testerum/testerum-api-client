package com.testerum.api_client.api.features;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.testerum.api_client.TesterumApiServiceLocator;
import com.testerum.api_client.infrastructure.TesterumApiInfrastructure;
import com.testerum.model.feature.Feature;
import com.testerum.model.feature.filter.FeaturesTreeFilter;
import com.testerum.model.feature.tree.RootFeatureNode;
import java.util.Collection;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeatureApiTest {

    private TesterumApiServiceLocator serviceLocator = TesterumApiInfrastructure.getTesterumApiServiceLocator();

    @BeforeEach
    void beforeAll() {
//        serviceLocator.setCurrentProject("C:\\temp\\tax");
        serviceLocator.setCurrentProject("C:\\programing\\workspace\\interviews\\bol\\mancala\\rest_api_test\\Mancala");

    }

    @Test
    void getAllFeatures() {
        Collection<Feature> allFeatures = serviceLocator.getFeatureApi().getAllFeatures();
        assertNotNull(allFeatures);
    }

    @Test
    void getFeaturesTree() {
        RootFeatureNode featuresTree = serviceLocator.getFeatureApi().getFeaturesTree(new FeaturesTreeFilter(
            true, true, true, null, Collections.emptyList()
        ));
        assertNotNull(featuresTree);
    }

    @Test
    void getFeatureAtPath() {
        Feature featureAtPath = serviceLocator.getFeatureApi().getFeatureAtPath("rest");
        assertNotNull(featureAtPath);
    }

    @Test
    void save() {
        Feature newFeature = serviceLocator.getFeatureApi().save("test/superman", Collections.emptyList());
        assertNull(newFeature);
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
