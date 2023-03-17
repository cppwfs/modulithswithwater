package io.spring.waterlevel.usgs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class StreamDataIntegrationTests {

    @Autowired
    UsgsService usgsService;

    @Test
    public void sampleIntegrationTest() {
        assertThat(usgsService.getStatusForSensor("123")).isEqualTo("Error Obtaining results for 123");
        assertThat(usgsService.getStatusForSensor("02311500")).contains("WITHLACOOCHEE RIVER NEAR DADE CITY, FL");
    }
}
