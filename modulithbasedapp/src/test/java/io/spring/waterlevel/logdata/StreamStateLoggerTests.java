package io.spring.waterlevel.logdata;

import io.spring.waterlevel.notifier.StreamDataStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
@ExtendWith(OutputCaptureExtension.class)
public class StreamStateLoggerTests {

    private  static final String DEFAULT_STATUS = "SOME SENSOR SOME WHERE";

    @Autowired
    private StreamStateLogger logger;

    @Test
    public void testLogger(CapturedOutput output) {
        StreamDataStatus status = new StreamDataStatus("123", "SOME SENSOR SOME WHERE");
        logger.on(status);
        assertThat(output.getOut()).contains(DEFAULT_STATUS);
    }
}
