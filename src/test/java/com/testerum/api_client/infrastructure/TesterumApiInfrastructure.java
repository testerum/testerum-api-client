package com.testerum.api_client.infrastructure;

import com.testerum.api_client.TesterumApiServiceLocator;

public class TesterumApiInfrastructure {

    private  static TesterumApiServiceLocator serviceLocator = TesterumApiServiceLocator.getInstance(9998);

    public static TesterumApiServiceLocator getTesterumApiServiceLocator () {
        return serviceLocator;
    }
}
