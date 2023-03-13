package io.spring.waterlevel.streamdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class StreamDataIntegrationTests {

    @Autowired
    StreamDataService streamDataService;

    @Test
    public void sampleIntegrationTest() {
        assertThat(streamDataService.getStatusForSensor("123")).isEqualTo("Error Obtaining results for 123");
        assertThat(streamDataService.getStatusForSensor("02311500")).contains("WITHLACOOCHEE RIVER NEAR DADE CITY, FL");
    }
}
