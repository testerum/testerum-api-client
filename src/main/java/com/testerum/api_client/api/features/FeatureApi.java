package com.testerum.api_client.api.features;

import com.testerum.model.feature.Feature;
import com.testerum.model.feature.filter.FeaturesTreeFilter;
import com.testerum.model.feature.tree.RootFeatureNode;
import com.testerum.model.infrastructure.path.Path;
import feign.Param;
import feign.RequestLine;
import java.util.Collection;

public interface FeatureApi {

    @RequestLine("GET /features")
    Collection<Feature> getAllFeatures();

    @RequestLine("POST /features/tree")
    RootFeatureNode getFeaturesTree(FeaturesTreeFilter filter);

    @RequestLine("GET /features/path?path={path}")
    Feature getFeatureAtPath(@Param("path") String path);

    @RequestLine("POST /features?path={path}")
    Feature save(@Param("path") String featurePart);

    @RequestLine("DELETE /features/delete?path={path}")
    void delete(String path);

    @RequestLine("POST /features/copy?sourcePath={sourcePath}&destinationPath={destinationPath}")
    Path copy(
        @Param("sourcePath") String sourcePath,
        @Param("destinationPath") String destinationPath);

    @RequestLine("POST /features/move?sourcePath={sourcePath}&destinationPath={destinationPath}")
    Path move(
        @Param("sourcePath") String sourcePath,
        @Param("destinationPath") String destinationPath);
}
