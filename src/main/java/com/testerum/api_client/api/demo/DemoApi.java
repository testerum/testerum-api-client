package com.testerum.api_client.api.demo;

import com.testerum.model.home.Project;
import feign.RequestLine;

public interface DemoApi {

    @RequestLine("POST demo/start")
    Project startDemoApp();
}
