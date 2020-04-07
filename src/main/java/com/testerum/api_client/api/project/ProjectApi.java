package com.testerum.api_client.api.project;

import com.testerum.model.home.CreateProjectRequest;
import com.testerum.model.home.Project;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface ProjectApi {

    @RequestLine("GET")
    List<Project> getProjects();

    @RequestLine("POST")
    Project createProject(CreateProjectRequest createProjectRequest);

    @RequestLine("POST /open")
    Project openProject(@Param("path") String path);

    @RequestLine("POST /reload")
    void reloadProject(@Param("path") String path);

    @RequestLine("POST /recent")
    void deleteRecentProject(@Param("path") String path);

    @RequestLine("PUT /rename")
    void renameProject(Project project);
}
