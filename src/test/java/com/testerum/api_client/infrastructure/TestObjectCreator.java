package com.testerum.api_client.infrastructure;

import static java.util.Arrays.asList;

import com.testerum.model.enums.StepPhaseEnum;
import com.testerum.model.infrastructure.path.Path;
import com.testerum.model.step.ComposedStepDef;
import com.testerum.model.step.StepCall;
import com.testerum.model.test.TestModel;
import com.testerum.model.test.TestProperties;
import com.testerum.model.test.scenario.Scenario;
import com.testerum.model.test.scenario.param.ScenarioParam;
import com.testerum.model.test.scenario.param.ScenarioParamType;
import com.testerum.model.text.StepPattern;
import com.testerum.model.text.parts.TextStepPatternPart;
import java.util.Collections;

public class TestObjectCreator {

    public static TestModel getTest() {
        return new TestModel (
            "tempTest",
            Path.Companion.createInstance(""),
            null,
            new TestProperties(false, false),
            "tempTestDescription",
            asList("temp_test"),
            asList(
                new Scenario(
                    "tempScenario",
                     asList(new ScenarioParam("tempScenarioName", ScenarioParamType.TEXT, "Temp Scenarioa Value")),
                    true
                )
            ),
            asList(
                new StepCall(
                    "tempStepCallId",
                    new ComposedStepDef(
                        Path.Companion.createInstance(""),
                        null,
                        StepPhaseEnum.GIVEN,
                        new StepPattern(asList(new TextStepPatternPart("temp Composed Step"))),
                        "temp composed step description",
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList()
                    ),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    true
                )
            ),
            Collections.emptyList()
        );
    }
}
