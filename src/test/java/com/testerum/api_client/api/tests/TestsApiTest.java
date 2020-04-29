package com.testerum.api_client.api.tests;

import static com.testerum.api_client.infrastructure.TesterumApiInfrastructure.TESTERUM_INTEGRATION_TESTS;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.testerum.api_client.TesterumApiServiceLocator;
import com.testerum.api_client.infrastructure.TestObjectCreator;
import com.testerum.api_client.infrastructure.TesterumApiInfrastructure;
import com.testerum.model.feature.filter.FeaturesTreeFilter;
import com.testerum.model.feature.tree.ContainerFeatureNode;
import com.testerum.model.feature.tree.FeatureNode;
import com.testerum.model.feature.tree.RootFeatureNode;
import com.testerum.model.feature.tree.TestFeatureNode;
import com.testerum.model.home.Project;
import com.testerum.model.test.TestModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestsApiTest {

    private TesterumApiServiceLocator serviceLocator = TesterumApiInfrastructure.getTesterumApiServiceLocator();

    @BeforeEach
    void beforeEach() {
        Project temporaryProject = TesterumApiInfrastructure.createTemporaryProject();
        serviceLocator.setCurrentProject(temporaryProject.getPath());
    }

    @Test
    void testGetTestAtPathComplexProject() {
        Project project = serviceLocator.getProjectApi().openProject(TESTERUM_INTEGRATION_TESTS);
        serviceLocator.setCurrentProject(project.getPath());

        RootFeatureNode featuresTree = serviceLocator.getFeatureApi().getFeaturesTree(
            new FeaturesTreeFilter(true, true, true, null, Collections.EMPTY_LIST));

        List<TestFeatureNode> tests = getTestsFromFeatureRoot(featuresTree);

        for (TestFeatureNode test : tests) {
            TestModel testAtPath = serviceLocator.getTestApi().getTestAtPath(test.getPath().toString());
            assertNotNull(testAtPath);
        }
    }

    private List<TestFeatureNode> getTestsFromFeatureRoot(ContainerFeatureNode featuresTree) {
        List<TestFeatureNode> result = new ArrayList<>();
        for (FeatureNode child : featuresTree.getChildren()) {
            if (child instanceof ContainerFeatureNode) {
                result.addAll(
                    getTestsFromFeatureRoot((ContainerFeatureNode) child)
                );
            } else if (child instanceof TestFeatureNode) {
                result.add((TestFeatureNode) child);
            }
        }
        return result;
    }

    @Test
    void testGetTestAtPath() {

        TestModel savedTest = serviceLocator.getTestApi().save(
            TestObjectCreator.getTest()
        );

        TestModel testAtPath = serviceLocator.getTestApi().getTestAtPath(savedTest.getPath().toString());

        assertNotNull(testAtPath);
    }

    @Test
    void getWarnings() {

        TestModel savedTest = serviceLocator.getTestApi().save(
            TestObjectCreator.getTest()
        );

        TestModel testAtPath = serviceLocator.getTestApi().getWarnings(savedTest);

        assertNotNull(testAtPath);
    }

    @Test
    void save() {
        TestModel testToSave = TestObjectCreator.getTest();

        TestModel savedTest = serviceLocator.getTestApi().save(
            testToSave
        );

        assertNotNull(savedTest);
    }

    @Test
    void delete() {
        TestModel savedTest = serviceLocator.getTestApi().save(
            TestObjectCreator.getTest()
        );

        serviceLocator.getTestApi().delete(savedTest.getPath().toString());
    }
}
