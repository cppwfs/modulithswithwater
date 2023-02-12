package io.spring.waterlevel.streamdata;

import io.spring.waterlevel.logdata.StreamStateLogger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class StreamDataIntegrationTests {

    @Autowired
    StreamDataService streamDataService;

    /**
     * This will not autowire because the integration test defaults to `STANDALONE` which means it will only focus on the current module.
     * If you needed it you would have to set the bootstrap mode to "ALL_DEPENDENCIES".  And that maybe a signal for tight coupling between modules.
     */
//    @Autowired
//    StreamStateLogger streamStateLogger;

    @Test
    public void sampleIntegrationTest() {
        assertThat(streamDataService.getStatusForSensor("123")).isEqualTo("Error Obtaining results for 123");
        assertThat(streamDataService.getStatusForSensor("02335757")).contains("BIG CREEK BELOW HOG WALLOW CREEK AT ROSWELL, GA");
    }
}
