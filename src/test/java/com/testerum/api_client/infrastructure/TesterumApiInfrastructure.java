package com.testerum.api_client.infrastructure;

import com.testerum.api_client.TesterumApiServiceLocator;
import com.testerum.model.home.CreateProjectRequest;
import com.testerum.model.home.Project;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class TesterumApiInfrastructure {

    public static String TEST_PROJECT_LOCATION = "c:\\programing\\workspace\\testerum-api-client\\testerum_projects";
    public static String TEMPORARY_PROJECT_NAME = "TempProject";

    private static TesterumApiServiceLocator serviceLocator = TesterumApiServiceLocator.getInstance(9999);

    public static TesterumApiServiceLocator getTesterumApiServiceLocator() {
        return serviceLocator;
    }

    public static Project createTemporaryProject() {
        try {
            FileUtils.deleteDirectory(new File(TEST_PROJECT_LOCATION + "\\" + TEMPORARY_PROJECT_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serviceLocator.getProjectApi().createProject(
            new CreateProjectRequest(TEST_PROJECT_LOCATION, TEMPORARY_PROJECT_NAME)
        );
    }
}
