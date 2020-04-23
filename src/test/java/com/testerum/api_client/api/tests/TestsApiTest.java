package com.testerum.api_client.api.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.testerum.api_client.TesterumApiServiceLocator;
import com.testerum.api_client.infrastructure.TestObjectCreator;
import com.testerum.api_client.infrastructure.TesterumApiInfrastructure;
import com.testerum.model.home.Project;
import com.testerum.model.test.TestModel;
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
