package com.testerum.api_client.api.project;

import com.testerum.model.home.CreateProjectRequest;
import com.testerum.model.home.Project;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface ProjectApi {

    @RequestLine("GET /projects")
    List<Project> getProjects();

    @RequestLine("POST /projects")
    Project createProject(CreateProjectRequest createProjectRequest);

    @RequestLine("POST /projects/open?path={path}")
    Project openProject(@Param("path") String path);

    @RequestLine("POST /projects/reload?path={path}")
    void reloadProject(@Param("path") String path);

    @RequestLine("POST /projects/recent?path={path}")
    void deleteRecentProject(@Param("path") String path);

    @RequestLine("PUT /projects/rename")
    void renameProject(Project project);
}
