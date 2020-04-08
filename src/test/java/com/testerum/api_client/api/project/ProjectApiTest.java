package com.testerum.api_client.api.project;

import static org.junit.jupiter.api.Assertions.*;

import com.testerum.api_client.TesterumApiServiceLocator;
import com.testerum.api_client.infrastructure.TesterumApiInfrastructure;
import com.testerum.model.home.CreateProjectRequest;
import com.testerum.model.home.Project;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectApiTest {

    private TesterumApiServiceLocator serviceLocator = TesterumApiInfrastructure.getTesterumApiServiceLocator();

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

    @Test
    void openProject() {
        List<Project> projects = serviceLocator.getProjectApi().getProjects();

        serviceLocator.getProjectApi().openProject(projects.get(0).getPath());
    }

    @Test
    void reloadProject() {
        List<Project> projects = serviceLocator.getProjectApi().getProjects();

        serviceLocator.getProjectApi().reloadProject(projects.get(0).getPath());
    }

    @Test
    void renameProject() {
        List<Project> projects = serviceLocator.getProjectApi().getProjects();

        serviceLocator.getProjectApi().renameProject(projects.get(0));
    }
}
