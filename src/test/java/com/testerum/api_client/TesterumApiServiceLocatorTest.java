package com.testerum.api_client;

import static org.junit.jupiter.api.Assertions.*;

import com.testerum.model.home.CreateProjectRequest;
import com.testerum.model.home.Project;
import java.util.List;
import org.junit.jupiter.api.Test;

class TesterumApiServiceLocatorTest {

    TesterumApiServiceLocator serviceLocator = TesterumApiServiceLocator.getInstance(9998);

    @Test
    void getProjects() {
        List<Project> projects = serviceLocator.getProjectApi().getProjects();
        assertNotNull(projects);
    }

    @Test
    void createProject() {
        List<Project> projects = serviceLocator.getProjectApi().getProjects();

        Project project = serviceLocator.getProjectApi().createProject(
            new CreateProjectRequest(projects.get(0).getPath(), projects.get(0).getName())
        );
        assertNotNull(project);
    }
}
