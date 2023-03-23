package io.spring.waterlevel.wateradvisor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest(mode = ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
public class WaterLevelTest {

    @Autowired
    WaterLevelService waterLevelService;

    @Test
    public void waterLevelStreamSensorTest() {
        assertThat(waterLevelService.getStatusForStreamSensor("123")).isEqualTo("Error Obtaining results for 123");
        assertThat(waterLevelService.getStatusForStreamSensor("02311500")).contains("WITHLACOOCHEE RIVER NEAR DADE CITY, FL");

    }
}
