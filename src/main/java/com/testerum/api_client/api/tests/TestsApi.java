package com.testerum.api_client.api.tests;

import com.testerum.model.test.TestModel;
import feign.Param;
import feign.RequestLine;

public interface TestsApi {

    @RequestLine("GET /tests?path={path}")
    TestModel getTestAtPath(@Param("path") String path);

    @RequestLine("POST /tests/warnings")
    TestModel getWarnings(TestModel test);

    @RequestLine("POST /tests/save")
    TestModel save(TestModel test);

    @RequestLine("DELETE /tests?path={path}")
    void delete(@Param("path") String path);
}
