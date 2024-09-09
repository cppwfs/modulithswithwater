package io.spring.waterlevel.park;

import io.spring.waterlevel.wateradvisor.StreamDataStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
@ExtendWith(OutputCaptureExtension.class)
public class ParkServiceTests {

    private  static final String DEFAULT_STATUS = "SOME SENSOR SOME WHERE";

    @Autowired
    private ParkService parkService;

    @Test
    public void testLogger(CapturedOutput output) {
        StreamDataStatus status = new StreamDataStatus("123", DEFAULT_STATUS);
        parkService.on(status);
        assertThat(output).contains(DEFAULT_STATUS);
    }
}
